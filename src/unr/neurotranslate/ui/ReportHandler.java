package unr.neurotranslate.ui;

import java.io.FileNotFoundException;

import org.gnome.gdk.EventExpose;
import org.gnome.gtk.Button;
import org.gnome.gtk.Entry;
import org.gnome.gtk.TreeSelection;
import org.gnome.gtk.TreeView;
import org.gnome.gtk.Widget;
import org.gnome.gtk.Button.Clicked;
import org.gnome.gtk.Entry.Activate;
import org.gnome.gtk.TreeSelection.Changed;

import unr.neurotranslate.ncs.Report;
import unr.neurotranslate.ui.controller.UIControllerNCS;

public class ReportHandler {
	
	public TreeSelection rs1;
	public String selectedText;
	public Report currentReport;
	
	public ReportHandler(final WidgetReferences w, final UIControllerNCS ui) throws FileNotFoundException {
		
		w.getW("reportScroll").connect(new Widget.ExposeEvent() {
			
			@Override
			public boolean onExposeEvent(Widget arg0, EventExpose arg1) {
				
				// fill out all entries/lists/combo boxes
				w.getL("rReports").listToModel(ui.getReports());			
				
				if( w.getC("rColSel").getChanged() ) {
					w.getC("rColSel").listToModel(ui.getColumns());
					w.getC("rColSel").setChanged(false);
				}
			
				
				if( w.getC("rLaySel").getChanged() ) {
					w.getC("rLaySel").listToModel(ui.getLayers());
					w.getC("rLaySel").setChanged(false);
				}
				
				
				if( w.getC("rCellSel").getChanged() ) {
					w.getC("rCellSel").listToModel(ui.getCells());
					w.getC("rCellSel").setChanged(false);
				}
				
				
				if( w.getC("rCompSel").getChanged() ) {
					w.getC("rCompSel").listToModel(ui.getCompartments());
					w.getC("rCompSel").setChanged(false);
				}
			
				return false;
			}
		});

		setEntries(w, ui);
		
		modifyHandlers(w, ui);
				
	}
	
	public void setEntries(final WidgetReferences w, final UIControllerNCS ui) throws FileNotFoundException {
		
		// Entries are set depending on report selected
		TreeView repView = w.getL("rReports").getView();
		
		// Connect for getting selected row in tree view		
		rs1 = repView.getSelection();
		rs1.connect(new Changed() {
			
			@Override
			public void onChanged(TreeSelection arg0) {	
			
				// Get selected string
				if( rs1.getSelected() != null ) {
					
					// Get selected report
					selectedText = w.getL("rReports").getModel().getValue(rs1.getSelected(), w.getL("rReports").getHeader());
					
					// Get current report based on selected report 
					try {
					  currentReport = ui.getReportByType(selectedText);
					} catch (Exception e) {					
						e.printStackTrace();
					}									
				
					// Set everything else to report					
					((Entry) w.getW("rType")).setText(currentReport.type);
					if(currentReport.prob != null) {
						//((Entry) w.getW("rProb")).setText(currentReport.prob.toString());	
					}
					else {
						//((Entry) w.getW("rProb")).setText("");
					}
					
					if(currentReport.frequency != null) {
						//((Entry) w.getW("rFreq")).setText(currentReport.frequency.toString());		
					}
					else {
						//((Entry) w.getW("rFreq")).setText("");
					}
				
					if(currentReport.cellSequence != null) {
						//((Entry) w.getW("rCellSeq")).setText(currentReport.cellSequence);		
					}
					else {
						//((Entry) w.getW("rCellSeq")).setText("");
					}
				
					if(currentReport.reportOn != null) {
						//((Entry) w.getW("rReportOn")).setText(currentReport.reportOn);
					}
					else {
						//((Entry) w.getW("rReportOn")).setText("");
					}
					
					if(currentReport.filename != null) {
						((Entry)w.getW("rFile")).setText(currentReport.filename);							
					}
					
					else {
						((Entry) w.getW("rFile")).setText("");
					}
					
					if(currentReport.timeStart != null ) {
						((Entry) w.getW("rTStart")).setText(currentReport.timeStart.toString()); // TODO - returns empty	
					}
					
					else {
						((Entry) w.getW("rTStart")).setText("");
					}
					
					if(currentReport.timeEnd != null) {
						((Entry) w.getW("rTEnd")).setText(currentReport.timeEnd.toString()); 	 // TODO - returns empty		
					}
				
					else {
						((Entry) w.getW("rTEnd")).setText("");
					}
				}					
			}
		});
		
	}
	
	public void modifyHandlers(final WidgetReferences w, final UIControllerNCS ui) throws FileNotFoundException {		
		
		// Connect for adding a report
		((Button)w.getW("rAddRep")).connect( new Clicked() {
			
			@Override
			public void onClicked(Button arg0) {
				
				currentReport = ui.addReport();				
				w.getL("rReports").addData( currentReport.type );
				currentReport.frequency = 0;
				currentReport.prob = 0.0;
				
			}
		});		
		
		// Connect for removing a report
		((Button)w.getW("rRemRep")).connect( new Clicked() {
			
			@Override
			public void onClicked(Button arg0) {
			
				// Remove selected report
				try {
					ui.removeReport(w.getL("rReports").getSelected());
				} catch (Exception e) {					
					e.printStackTrace();
				}			
				w.getL("rReports").removeData( );
				((Entry) w.getW("rType")).setText("");
				((Entry) w.getW("rProb")).setText("");
				((Entry) w.getW("rFreq")).setText("");
				((Entry) w.getW("rCellSeq")).setText("");
				((Entry) w.getW("rReportOn")).setText("");
				((Entry) w.getW("rFile")).setText("");
				((Entry) w.getW("rTStart")).setText("");
				((Entry) w.getW("rTEnd")).setText("");
			}
		});	
		
		// rType
		((Entry) w.getW("rType")).connect( new Activate() {
			
			@Override
			public void onActivate(Entry arg0) {						
				w.getL("rReports").removeData();		
				w.getL("rReports").addData(arg0.getText());	
				w.getL("rReports").getView().grabFocus();
				currentReport.type = arg0.getText();	
				Utils.setColor("rType", Utils.activeGreen, w);
			}
		});
		
		// Probability
		((Entry) w.getW("rProb")).connect( new Activate() {
			
			@Override
			public void onActivate(Entry arg0) {
				
				try {
					double d = Double.parseDouble(arg0.getText());
					currentReport.prob = d;
					Utils.setColor("rProb", Utils.activeGreen, w);
				} catch( NumberFormatException nfe ) {
					Utils.setColor("rProb", Utils.red, w);
					arg0.setText("");
				}
			}
		});
		
		// Frequency
		((Entry) w.getW("rFreq")).connect( new Activate() {
			
			@Override
			public void onActivate(Entry arg0) {
				
				try {
					double d = Double.parseDouble(arg0.getText());
					//currentReport.frequency = d;
					Utils.setColor("rFreq", Utils.activeGreen, w);
				} catch( NumberFormatException nfe ) {
					Utils.setColor("rFreq", Utils.red, w);
					arg0.setText("");
				}
			}
		});
		
		// Cell Sequence
		((Entry) w.getW("rCellSeq")).connect( new Activate() {
			
			@Override
			public void onActivate(Entry arg0) {
		
				currentReport.cellSequence = arg0.getText();
				Utils.setColor("rFreq", Utils.red, w);
		
			}
		});
		
		// Report on
		((Entry) w.getW("rReportOn")).connect( new Activate() {
			
			@Override
			public void onActivate(Entry arg0) {
				
				currentReport.reportOn = arg0.getText();
			}
		});
		
		// Filename
		((Entry) w.getW("rFile")).connect( new Activate() {
			
			@Override
			public void onActivate(Entry arg0) {
				
				currentReport.filename = arg0.getText();
			}
		});
		
		// Time Start
		
		// Time End
		
	}
}
