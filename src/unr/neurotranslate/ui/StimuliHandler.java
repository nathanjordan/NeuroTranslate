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

	// All array lists are for debugging
	public ArrayList<String> sInjects;
	public ArrayList<String> sStimuli;
	public TreeSelection rs1, rs2;
	public String selectedText;
	
	public ListEntity injects;
	public ListEntity stimuli;
	public Entry sType;
	public Entry sProb;
	public Entry stType;
	public Entry stMode;
	public Entry stPattern;
	public Entry stTimeInc;
	public Entry stFreqCol;
	public Entry stCellFreq;
	public Entry stDynRange;
	public Entry stAmpStart;
	public Entry stWidth;
	public Entry stTStart;
	public Entry stTEnd;
	public StimulusInject currentStimInj;
	public Stimulus currentStimulus;
	
		
	public StimuliHandler(WidgetReferences w, UIControllerNCS ui) throws FileNotFoundException {
		
		ScrolledWindow c = (ScrolledWindow) GladeParseUtil.grabWidget("scrolledwindow3", "window1");
		
		c.connect(new Widget.ExposeEvent() {
			
			@Override
			public boolean onExposeEvent(Widget arg0, EventExpose arg1) {
				
				// fill out all entries/lists/combo boxes
				
				
				
				return false;
			}
		});
		
		// Data sources
		sInjects = new ArrayList<String>();
		sStimuli = new ArrayList<String>();
		selectedText = new String();
		
		// Entries
		sType = (Entry) GladeParseUtil.grabWidget( "entry18", "window1" );		
		sProb = (Entry) GladeParseUtil.grabWidget( "entry86", "window1" );
		stType = (Entry) GladeParseUtil.grabWidget( "entry19", "window1" );
		stMode = (Entry) GladeParseUtil.grabWidget( "entry6", "window1" );
		stPattern = (Entry) GladeParseUtil.grabWidget( "entry16", "window1" );
		stTimeInc = (Entry) GladeParseUtil.grabWidget( "entry21", "window1" );
		stFreqCol = (Entry) GladeParseUtil.grabWidget( "entry20", "window1" );
		stCellFreq = (Entry) GladeParseUtil.grabWidget( "entry23", "window1" );
		stDynRange = (Entry) GladeParseUtil.grabWidget( "entry22", "window1" );
		stAmpStart = (Entry) GladeParseUtil.grabWidget( "entry24", "window1" );
		stWidth = (Entry) GladeParseUtil.grabWidget( "entry25", "window1" );
		stTStart = (Entry) GladeParseUtil.grabWidget( "entry26", "window1" );
		stTEnd = (Entry) GladeParseUtil.grabWidget( "entry27", "window1" );
		
		// Lists		
		injects = new ListEntity( "Stimulus Injects", "window1" );
		stimuli = new ListEntity( "Stimuli", "window1" );		
		
		setLists();
		
		setEntries();
		
		modifyHandlers();
		
	}
	
	public void setLists() throws FileNotFoundException {
	
		
				
	}
	
	public void setEntries() throws FileNotFoundException {
	
		// Entries are set depending on stimulus inject selected
		TreeView stimInView = injects.getView();		
		
		// Connect for getting selected row in tree view		
		rs1 = stimInView.getSelection();
		rs1.connect(new Changed() {
			
			@Override
			public void onChanged(TreeSelection arg0) {	
			
				// Get selected string
				if( rs1.getSelected() != null ) {
					
					// Get selected column
					selectedText = injects.getModel().getValue(rs1.getSelected(), injects.getHeader());
					
					// Get current inject based on selected inject 
					try {
					 // currentStimInj = getStimulusInjectByType(selectedText);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}									
				
					// Set everything else to current inject
					// TODO - fill combo boxes
					sType.setText(currentStimInj.type);
					sProb.setText(currentStimInj.probability.toString());
					
				}							
			}
		});
		
		// Entries are set depending on stimulus inject selected
		TreeView stimuliView = stimuli.getView();		
		
		// Connect for getting selected row in tree view		
		rs2 = stimuliView.getSelection();
		rs2.connect(new Changed() {
			
			@Override
			public void onChanged(TreeSelection arg0) {	
			
				// Get selected string
				if( rs2.getSelected() != null ) {
					
					// Get selected column
					selectedText = stimuli.getModel().getValue(rs2.getSelected(), stimuli.getHeader());
					
					// Get current inject based on selected inject 
					try {
					// currentStimulus = getStimulusByType(selectedText);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}									
				
					// Set everything else to current inject	
					/*stType.setText(currentStimulus.type);
					stMode.setText(currentStimulus.mode);
					stPattern.setText(currentStimulus.pattern);
					stTimeInc.setText(currentStimulus.timeIncrement.toString());
					stFreqCol.setText(currentStimulus.freqCols.toString());
					stCellFreq.setText(currentStimulus.cellsPerFreq.toString());
					stDynRange.setText(currentStimulus.dynRange.mean + " " + currentStimulus.dynRange.stdev);
					stAmpStart.setText(currentStimulus.ampStart.toString());
					stWidth.setText(currentStimulus.width.toString());
					// TODO - why do these two  return a arraylist<double>?
					stTStart.setText(currentStimulus.timeStart.toString());
					stTEnd.setText(currentStimulus.timeEnd.toString());*/
				}							
			}
		});
	}
	
	public void modifyHandlers() throws FileNotFoundException {
	
		//Buttons for add/removing
		Button addIn = (Button) GladeParseUtil.grabWidget( "sSimInAdd", "window1" );
		Button remIn = (Button) GladeParseUtil.grabWidget( "sSimInRem", "window1" );
		Button addSim = (Button) GladeParseUtil.grabWidget( "sSimAdd", "window1" );
		Button remSim = (Button) GladeParseUtil.grabWidget( "sSimRem", "window1" );
		
		// Connect for adding an inject
		addIn.connect( new Clicked() {
			
			@Override
			public void onClicked(Button arg0) {
				
				injects.addData( "new inject");
				
			}
		});		
		
		// Connect for removing an inject
		remIn.connect( new Clicked() {
			
			@Override
			public void onClicked(Button arg0) {
			
				injects.removeData( );
				
			}
		});	
		
		// Connect for adding a stimulus
		addSim.connect( new Clicked() {
			
			@Override
			public void onClicked(Button arg0) {
				
				stimuli.addData( "new stimulus" );
			}
		});
		
		// Connect for removing a stimulus
		remSim.connect(new Clicked() {
			
			@Override
			public void onClicked(Button arg0) {
				
				stimuli.removeData();
				
			}
		});
	
	}

}
