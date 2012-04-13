package unr.neurotranslate.ui;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import org.gnome.gdk.EventExpose;
import org.gnome.gtk.Button;
import org.gnome.gtk.Entry;
import org.gnome.gtk.Label;
import org.gnome.gtk.Notebook;
import org.gnome.gtk.ScrolledWindow;
import org.gnome.gtk.TreeSelection;
import org.gnome.gtk.TreeView;
import org.gnome.gtk.Widget;
import org.gnome.gtk.Button.Clicked;
import org.gnome.gtk.TreeSelection.Changed;

import unr.neurotranslate.ncs.SynFacilDepress;
import unr.neurotranslate.ncs.SynLearning;
import unr.neurotranslate.ncs.Synapse;
import unr.neurotranslate.ui.controller.UIControllerNCS;

public class SynapseHandler {
	// All array lists are for debugging
	public ArrayList<String> sSynap;
	public ArrayList<String> sfd;
	public ArrayList<String> ll;
	public String selectedText;
	public TreeSelection rs1, rs2, rs3;
	
	public ListEntity synapses;
	public ListEntity SFDLabels;
	public ListEntity LearnLabels;	
	public Entry syType;
	public Entry sySYN;
	public Entry syHStart;
	public Entry syHEnd;
	public Entry syAbMean;
	public Entry syAbStd;
	public Entry syMCMean;
	public Entry syMCStd;
	public Entry syDMin;
	public Entry syDMax;
	public Entry sySRMean;
	public Entry sySRStd;
	public Entry sySFDType;
	public Entry sySFD;
	public Entry sySFDFTMean;
	public Entry sySFDFTStd;
	public Entry sySFDDTMean;
	public Entry sySFDDTStd;
	public Entry syLLType;
	public Entry syLLLearning;
	public Entry syLLFTMean;
	public Entry syLLFTStd;
	public Entry syLLDTMean;
	public Entry syLLDTStd;
	public Entry syLLNebWinMean;
	public Entry syLLNebWinStd;
	public Entry syLLPosWinMean;
	public Entry syLLPosWinStd;
	public Entry syLLPosPeakMean;
	public Entry syLLPosPeakStd;
	public Entry syLLNegMean;
	public Entry syLLNegStd;
	public Synapse currentSynapse;	
	public SynFacilDepress currentSFD;
	public SynLearning currentLearn;
	
	public SynapseHandler(WidgetReferences w, UIControllerNCS ui) throws FileNotFoundException {
		
		ScrolledWindow c = (ScrolledWindow) GladeParseUtil.grabWidget("scrolledwindow5", "window1");
		
		c.connect(new Widget.ExposeEvent() {
			
			@Override
			public boolean onExposeEvent(Widget arg0, EventExpose arg1) {
				
				// fill out all entries/lists/combo boxes
				
				
				
				return false;
			}
		});
		
		// Debugging model
		sSynap = new ArrayList<String>();
		sfd = new ArrayList<String>();
		ll = new ArrayList<String>();
		selectedText = new String();
		
		// Entries
		syType = (Entry) GladeParseUtil.grabWidget("entry43", "window1");
		sySYN = (Entry) GladeParseUtil.grabWidget("entry41", "window1");
		syHStart = (Entry) GladeParseUtil.grabWidget("entry42", "window1");
		syHEnd = (Entry) GladeParseUtil.grabWidget("entry45", "window1");
		syAbMean = (Entry) GladeParseUtil.grabWidget("entry34", "window1");
		syAbStd = (Entry) GladeParseUtil.grabWidget("entry35", "window1");
		syMCMean = (Entry) GladeParseUtil.grabWidget("entry36", "window1");
		syMCStd = (Entry) GladeParseUtil.grabWidget("entry37", "window1");
		syDMin = (Entry) GladeParseUtil.grabWidget("entry38", "window1");
		syDMax = (Entry) GladeParseUtil.grabWidget("entry39", "window1");
		sySRMean = (Entry) GladeParseUtil.grabWidget("entry46", "window1");
		sySRStd = (Entry) GladeParseUtil.grabWidget("entry47", "window1");
		sySFDType = (Entry) GladeParseUtil.grabWidget("entry44", "window1");
		sySFD = (Entry) GladeParseUtil.grabWidget("entry50", "window1");
		sySFDFTMean = (Entry) GladeParseUtil.grabWidget("entry51", "window1");
		sySFDFTStd = (Entry) GladeParseUtil.grabWidget("entry52", "window1");
		sySFDDTMean = (Entry) GladeParseUtil.grabWidget("entry53", "window1");
		sySFDDTStd = (Entry) GladeParseUtil.grabWidget("entry55", "window1");
		syLLType = (Entry) GladeParseUtil.grabWidget("entry54", "window1");
		syLLLearning = (Entry) GladeParseUtil.grabWidget("entry66", "window1");
		syLLFTMean = (Entry) GladeParseUtil.grabWidget("entry67", "window1");
		syLLFTStd = (Entry) GladeParseUtil.grabWidget("entry68", "window1");
		syLLDTMean = (Entry) GladeParseUtil.grabWidget("entry69", "window1");
		syLLDTStd = (Entry) GladeParseUtil.grabWidget("entry70", "window1");
		syLLNebWinMean = (Entry) GladeParseUtil.grabWidget("entry71", "window1");
		syLLNebWinStd = (Entry) GladeParseUtil.grabWidget("entry72", "window1");
		syLLPosWinMean = (Entry) GladeParseUtil.grabWidget("entry73", "window1");
		syLLPosWinStd = (Entry) GladeParseUtil.grabWidget("entry74", "window1");
		syLLPosPeakMean = (Entry) GladeParseUtil.grabWidget("entry75", "window1");
		syLLPosPeakStd = (Entry) GladeParseUtil.grabWidget("entry76", "window1");
		syLLNegMean = (Entry) GladeParseUtil.grabWidget("entry77", "window1");
		syLLNegStd = (Entry) GladeParseUtil.grabWidget("entry78", "window1");
		
		// Lists
		synapses = new ListEntity( "SynapseList", "window1" );
		SFDLabels = new ListEntity( "SFDLabels", "window1" );
		LearnLabels = new ListEntity( "LearnLabels", "window1" );
		
		setLists();
		
		setEntries();
		
		modifyHandlers();
		
	}
	
	public void setLists() throws FileNotFoundException {
	
		// Entries are set depending on synapse selected
		TreeView synView = synapses.getView();		
		
		// Connect for getting selected row in tree view		
		rs1 = synView.getSelection();
		rs1.connect(new Changed() {
			
			@Override
			public void onChanged(TreeSelection arg0) {	
			
				// Get selected string
				if( rs1.getSelected() != null ) {
					
					// Get selected column
					selectedText = synapses.getModel().getValue(rs1.getSelected(), synapses.getHeader());
					
					// Get current synapse based on selected synapse 
					try {
						// currentSynapse = getSynapseByType(selectedText);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}									
				
					// Set everything else to current column 	
					// TODO - set SDF Label and Learn Label
					/*syType.setText(currentSynapse.type);
					sySYN.setText(currentSynapse.synPSGName);
					syHStart.setText(currentSynapse.hebbStart.toString());
					syHEnd.setText(currentSynapse.hebbEnd.toString());
					syAbMean.setText(currentSynapse.absoluteUse.mean.toString());
					syAbStd.setText(currentSynapse.absoluteUse.stdev.toString());
					syMCMean.setText(currentSynapse.maxConduct.mean.toString());
					syMCStd.setText(currentSynapse.maxConduct.stdev.toString());
					syDMin.setText(currentSynapse.delay.mean.toString());
					syDMax.setText(currentSynapse.delay.stdev.toString());*/					
				}							
			}
		});
		
		// Entries are set depending on SFD Label selected
		TreeView sfdView = SFDLabels.getView();		
		
		// Connect for getting selected row in tree view		
		rs2 = sfdView.getSelection();
		rs2.connect(new Changed() {
			
			@Override
			public void onChanged(TreeSelection arg0) {	
			
				// Get selected string
				if( rs2.getSelected() != null ) {
					
					// Get selected column
					selectedText = SFDLabels.getModel().getValue(rs2.getSelected(), SFDLabels.getHeader());
					
					// Get current sfd based on selected sfd 
					try {
					// currentSFD = getSFDLabelByType(selectedText);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}									
				
					/*// Set everything else to current column 	
					sySFDType.setText(currentSFD.type);
					sySFD.setText(currentSFD.SFD);
					sySFDFTMean.setText(currentSFD.facilTau.mean.toString());
					sySFDFTStd.setText(currentSFD.facilTau.stdev.toString());
					sySFDDTMean.setText(currentSFD.deprTau.mean.toString());
					sySFDDTStd.setText(currentSFD.deprTau.stdev.toString());*/
				}							
			}
		});
		
		// Entries are set depending on learn label selected
		TreeView learnView = LearnLabels.getView();		
		
		// Connect for getting selected row in tree view		
		rs3 = learnView.getSelection();
		rs3.connect(new Changed() {
			
			@Override
			public void onChanged(TreeSelection arg0) {	
			
				// Get selected string
				if( rs3.getSelected() != null ) {
					
					// Get selected column
					selectedText = LearnLabels.getModel().getValue(rs3.getSelected(), LearnLabels.getHeader());
					
					// Get current learn label based on selected learn label 
					try {
					// currentLearn = getCurrentLearnLabelByType(selectedText);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}									
				
					// Set everything else to current learn label 	
					/*syLLType.setText(currentLearn.type);
					syLLLearning.setText(currentLearn.learning);
					syLLNebWinMean.setText(currentLearn.negHebWindow.mean.toString());
					syLLNebWinStd.setText(currentLearn.negHebWindow.stdev.toString());
					syLLPosWinMean.setText(currentLearn.posHebWindow.mean.toString());
					syLLPosWinStd.setText(currentLearn.posHebWindow.stdev.toString());
					syLLPosPeakMean.setText(currentLearn.posHebPeakDeltaUse.mean.toString());
					syLLPosPeakStd.setText(currentLearn.posHebPeakDeltaUse.stdev.toString());
					syLLNegMean.setText(currentLearn.negHebPeakDeltaUse.mean.toString());
					syLLNegStd.setText(currentLearn.negHebPeakDeltaUse.stdev.toString());
					*/
					// TODO - where are facil and depre taus????? 
					// syLLFTMean.setText(currentLearn.facilTau.mean.toStrinG());
					// syLLFTStd.setText(currentLearn.facilTau.mean.toString());
					// syLLDTMean.setText(currentLearn.facilTau.stdev.toString());
					// syLLDTMean.setText(currentLearn.deprTau.mean.toString());
					// syLLDTStd.setText(currentLearn.deprTau.stdev.toString());
				}							
			}
		});
				
	}
	
	public void setEntries() {
		
	}
	
	public void modifyHandlers() throws FileNotFoundException {
	
		//Buttons for add/removing
		Button addSynapse = (Button) GladeParseUtil.grabWidget( "sSynapseAdd", "window1" );
		Button remSynapse = (Button) GladeParseUtil.grabWidget( "sSynapseRem", "window1" );
		Button addSFD = (Button) GladeParseUtil.grabWidget( "sSFDAdd", "window1" );
		Button remSFD = (Button) GladeParseUtil.grabWidget( "sSFDRem", "window1" );
		Button addLearn = (Button) GladeParseUtil.grabWidget( "sLearnAdd", "window1" );
		Button remLearn = (Button) GladeParseUtil.grabWidget( "sLearnRem", "window1" );
		
		// Connect for adding a cell
		addSynapse.connect( new Clicked() {
			
			@Override
			public void onClicked(Button arg0) {
				
				synapses.addData( "new synapse");
				
			}
		});		
		
		// Connect for removing a cell
		remSynapse.connect( new Clicked() {
			
			@Override
			public void onClicked(Button arg0) {
			
				synapses.removeData( );
				
			}
		});	
		
		// Connect for adding a compartment
		addSFD.connect( new Clicked() {
			
			@Override
			public void onClicked(Button arg0) {
				
				SFDLabels.addData( "new sfd label" );
			}
		});
		
		// Connect for removing a compartment
		remSFD.connect(new Clicked() {
			
			@Override
			public void onClicked(Button arg0) {
				
				SFDLabels.removeData();
				
			}
		});
		
		// Connect for adding a Spike Shape
		addLearn.connect( new Clicked() {
			
			@Override
			public void onClicked(Button arg0) {
				
				LearnLabels.addData( "new learn label" );
				
			}
		});
		
		// Connect for removing a Spike Shape
		remLearn.connect( new Clicked() {
			
			@Override
			public void onClicked(Button arg0) {
				
				LearnLabels.removeData();
				
			}
		});
	}

}
