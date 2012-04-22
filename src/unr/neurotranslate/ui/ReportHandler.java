package unr.neurotranslate.ui;

import java.io.FileNotFoundException;

import org.gnome.gdk.EventExpose;
import org.gnome.gtk.Button;
import org.gnome.gtk.Entry;
import org.gnome.gtk.TreeSelection;
import org.gnome.gtk.TreeView;
import org.gnome.gtk.Widget;
import org.gnome.gtk.Button.Clicked;
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
				w.getC("rColSel").listToModel(ui.getColumns());
				w.getC("rLaySel").listToModel(ui.getLayers());
				w.getC("rCellSel").listToModel(ui.getCells());
				w.getC("rCompSel").listToModel(ui.getCompartments());
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
					//((Entry) w.getW("rProb")).setText(currentReport.prob.toString());
					//((Entry) w.getW("rFreq")).setText(currentReport.frequency.toString());
					//((Entry) w.getW("rCellSeq")).setText(currentReport.cellSequence);
					//((Entry) w.getW("rReportOn")).setText(currentReport.reportOn);
					((Entry) w.getW("rFile")).setText(currentReport.filename);
					((Entry) w.getW("rTStart")).setText(currentReport.timeStart.toString()); // TODO - returns empty
					((Entry) w.getW("rTEnd")).setText(currentReport.timeEnd.toString()); 	 // TODO - returns empty
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
		
		// Type
		
		// Probability
		
		// Frequency
		
		// Cell Sequence
		
		// Report on
		
		// Filename
		
		// Time Start
		
		// Time End
		
	}
}
