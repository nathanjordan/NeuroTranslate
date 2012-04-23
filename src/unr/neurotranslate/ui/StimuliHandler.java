package unr.neurotranslate.ui;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import org.gnome.gdk.EventExpose;
import org.gnome.gtk.Button;
import org.gnome.gtk.Entry;
import org.gnome.gtk.ScrolledWindow;
import org.gnome.gtk.TreeSelection;
import org.gnome.gtk.TreeView;
import org.gnome.gtk.Widget;
import org.gnome.gtk.Button.Clicked;
import org.gnome.gtk.TreeSelection.Changed;

import unr.neurotranslate.ncs.Stimulus;
import unr.neurotranslate.ncs.StimulusInject;
import unr.neurotranslate.ui.controller.UIControllerNCS;

public class StimuliHandler {

	public TreeSelection rs1, rs2;
	public String selectedText;
	public StimulusInject currentStimInj;
	public Stimulus currentStimulus;
	
		
	public StimuliHandler(final WidgetReferences w, final UIControllerNCS ui) throws FileNotFoundException {			
		
		w.getW("stimuliScroll").connect(new Widget.ExposeEvent() {
			
			@Override
			public boolean onExposeEvent(Widget arg0, EventExpose arg1) {
				
				// fill out all entries/lists/combo boxes
				w.getL("stInjects").listToModel(ui.getStimulusInjects());
				w.getL("stStimuli").listToModel(ui.getStimuli());
				w.getC("stStimSel").listToModel(ui.getStimuli());
				w.getC("stColSel").listToModel(ui.getColumns());
				w.getC("stLaySel").listToModel(ui.getLayers());
				w.getC("stCellSel").listToModel(ui.getCells());
				w.getC("stCompSel").listToModel(ui.getCompartments());
				
				return false;
			}
		});
		
		setEntries(w, ui);
		
		modifyHandlers(w, ui);
		
	}
	
	public void setEntries( final WidgetReferences w, final UIControllerNCS ui) throws FileNotFoundException {
	
		// Entries are set depending on stimulus inject selected
		TreeView stimInView = w.getL("stInjects").getView();		
		
		// Connect for getting selected row in tree view		
		rs1 = stimInView.getSelection();
		rs1.connect(new Changed() {
			
			@Override
			public void onChanged(TreeSelection arg0) {	
			
				// Get selected string
				if( rs1.getSelected() != null ) {
					
					// Get selected column
					selectedText = w.getL("stInjects").getModel().getValue(rs1.getSelected(), w.getL("stInjects").getHeader());
					
					// Get current inject based on selected inject 
					try {
					  currentStimInj = ui.getStimulusInjectByType(selectedText);
					} catch (Exception e) {					
						e.printStackTrace();
					}									
				
					// Set everything else to current inject
					// TODO - fill combo boxes
					((Entry) w.getW("stSIType")).setText(currentStimInj.type);
					((Entry) w.getW("stProb")).setText(currentStimInj.probability.toString());
					
				}							
			}
		});
		
		// Entries are set depending on stimulus inject selected
		TreeView stimuliView =  w.getL("stStimuli").getView();		
		
		// Connect for getting selected row in tree view		
		rs2 = stimuliView.getSelection();
		rs2.connect(new Changed() {
			
			@Override
			public void onChanged(TreeSelection arg0) {	
			
				// Get selected string
				if( rs2.getSelected() != null ) {
					
					// Get selected column
					selectedText = w.getL("stStimuli").getModel().getValue(rs2.getSelected(), w.getL("stStimuli").getHeader());
					
					// Get current inject based on selected inject 
					try {
					 currentStimulus = ui.getStimulusByType(selectedText);
					} catch (Exception e) {					
						e.printStackTrace();
					}									
				
					// Set everything else to current inject	
					((Entry) w.getW("stSType")).setText(currentStimulus.type);
					((Entry) w.getW("stMode")).setText(currentStimulus.mode);
					((Entry) w.getW("stPattern")).setText(currentStimulus.pattern);
					((Entry) w.getW("stTimeInc")).setText(currentStimulus.timeIncrement.toString());
					//((Entry) w.getW("stFreqCol")).setText(currentStimulus.freqCols.toString());
					((Entry) w.getW("stCellFreq")).setText(currentStimulus.cellsPerFreq.toString());
					((Entry) w.getW("stDynRange")).setText(currentStimulus.dynRange.mean + " " + currentStimulus.dynRange.stdev);
					((Entry) w.getW("stAmpStart")).setText(currentStimulus.ampStart.toString());
					((Entry) w.getW("stWidth")).setText(currentStimulus.width.toString());				
					((Entry) w.getW("stTStart")).setText(currentStimulus.timeStart.toString());
					((Entry) w.getW("stTEnd")).setText(currentStimulus.timeEnd.toString());
				}							
			}
		});
	}
	
	public void modifyHandlers(final WidgetReferences w, final UIControllerNCS ui) throws FileNotFoundException {		
		
		// Connect for adding an inject
		((Button)w.getW("stAddIn")).connect( new Clicked() {
			
			@Override
			public void onClicked(Button arg0) {
				
				currentStimInj = ui.addStimulusInject();
				w.getL("stInjects").addData( currentStimInj.type );
				
			}
		});		
		
		// Connect for removing an inject
		((Button)w.getW("stRemIn")).connect( new Clicked() {
			
			@Override
			public void onClicked(Button arg0) {
			
				w.getL("stInjects").removeData( );
				
			}
		});	
		
		// Connect for adding a stimulus
		((Button)w.getW("stAddSim")).connect( new Clicked() {
			
			@Override
			public void onClicked(Button arg0) {
				
				currentStimulus = ui.addStimulus();
				w.getL("stStimuli").addData( currentStimulus.type );
			}
		});
		
		// Connect for removing a stimulus
		((Button)w.getW("stRemSim")).connect(new Clicked() {
			
			@Override
			public void onClicked(Button arg0) {
				
				w.getL("stStimuli").removeData();
				
			}
		});
	
	}

}
