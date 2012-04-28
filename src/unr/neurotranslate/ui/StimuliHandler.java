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
				
				if( w.getC("stStimSel").getChanged() ) {
					w.getC("stStimSel").listToModel(ui.getStimuli());
					w.getC("stStimSel").setChanged(false);
				}
				if( w.getC("stColSel").getChanged() ) {
					w.getC("stColSel").listToModel(ui.getColumns());
					w.getC("stColSel").setChanged(false);
				}
				
				if( w.getC("stLaySel").getChanged() ) {				
					w.getC("stLaySel").listToModel(ui.getLayers());
					w.getC("stLaySel").setChanged(false);
				}
				
				if( w.getC("stCellSel").getChanged() ) {					
					w.getC("stCellSel").listToModel(ui.getCells());
					w.getC("stCellSel").setChanged(false);
				}
			
				if( w.getC("stCompSel").getChanged() ){
					w.getC("stCompSel").listToModel(ui.getCompartments());
					w.getC("stCompSel").setChanged(false);
				}		
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
					((Entry) w.getW("stSIType")).setText(currentStimInj.type);
					if(currentStimInj.probability != null) {
						((Entry) w.getW("stProb")).setText(currentStimInj.probability.toString());	
					}
					else {
						((Entry) w.getW("stProb")).setText("");
					}
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
					if(currentStimulus.timeIncrement != null ) {
						((Entry) w.getW("stTimeInc")).setText(currentStimulus.timeIncrement.toString());		
					}
					else {
						((Entry) w.getW("stTimeInc")).setText("");
					}
									
					if(currentStimulus.cellsPerFreq != null ) {
						((Entry) w.getW("stCellFreq")).setText(currentStimulus.cellsPerFreq.toString());		
					}
					else {
						((Entry) w.getW("stCellFreq")).setText("");
					}
					
					if(currentStimulus.dynRange.mean != null || currentStimulus.dynRange.stdev != null ) {						
						((Entry) w.getW("stDynRange")).setText(currentStimulus.dynRange.mean + " " + currentStimulus.dynRange.stdev);
					}
					else {
						((Entry) w.getW("stDynRange")).setText("");
					}
					
					if(currentStimulus.ampStart != null) {
						((Entry) w.getW("stAmpStart")).setText(currentStimulus.ampStart.toString());	
					}
					else {
						((Entry) w.getW("stAmpStart")).setText("");								
					}
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
				currentStimInj.probability = 0.0;
				w.getL("stInjects").addData( currentStimInj.type );
				
				
			}
		});		
		
		// Connect for removing an inject
		((Button)w.getW("stRemIn")).connect( new Clicked() {
			
			@Override
			public void onClicked(Button arg0) {
			
				try {
					ui.removeStimulusInject( w.getL("stInjects").getSelected() );
				} catch (Exception e) {					
					e.printStackTrace();
				}
				w.getL("stInjects").removeData( );
				((Entry) w.getW("stSIType")).setText("");
				((Entry) w.getW("stProb")).setText("");
			
			}
		});	
		
		// Connect for adding a stimulus
		((Button)w.getW("stAddSim")).connect( new Clicked() {
			
			@Override
			public void onClicked(Button arg0) {
				
				currentStimulus = ui.addStimulus();
				currentStimulus.mode = currentStimulus.pattern = " ";
				currentStimulus.timeIncrement = currentStimulus.dynRange.mean = currentStimulus.dynRange.stdev = 0.0;
				currentStimulus.width = currentStimulus.ampStart = 0.0;
				currentStimulus.cellsPerFreq = 0;
				w.getL("stStimuli").addData( currentStimulus.type );
				w.getC("stStimSel").setChanged(true);
			}
		});
		
		// Connect for removing a stimulus
		((Button)w.getW("stRemSim")).connect(new Clicked() {
			
			@Override
			public void onClicked(Button arg0) {
				
				try {
					ui.removeStimulus( w.getL("stStimuli").getSelected());
				} catch (Exception e) {			
					e.printStackTrace();
				}
				w.getL("stStimuli").removeData();
				w.getC("stStimSel").setChanged(true);
				
				((Entry) w.getW("stSType")).setText("");
				((Entry) w.getW("stMode")).setText("");
				((Entry) w.getW("stPattern")).setText("");
				((Entry) w.getW("stTimeInc")).setText("");			
				((Entry) w.getW("stCellFreq")).setText("");
				((Entry) w.getW("stDynRange")).setText("");
				((Entry) w.getW("stAmpStart")).setText("");
				((Entry) w.getW("stWidth")).setText("");
				((Entry) w.getW("stTStart")).setText("");
				((Entry) w.getW("stTEnd")).setText("");				
			}
		});	
	}

}
