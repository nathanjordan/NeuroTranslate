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
	
	/*
	 * public ListEntity synapses;
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
	*/
	public Synapse currentSynapse;	
	public SynFacilDepress currentSFD;
	public SynLearning currentLearn;
	
	public SynapseHandler(final WidgetReferences w, final UIControllerNCS ui) throws FileNotFoundException {
					
		w.getW("synapseScroll").connect(new Widget.ExposeEvent() {
			
			@Override
			public boolean onExposeEvent(Widget arg0, EventExpose arg1) {
				
				// fill out all entries/lists/combo boxes
				w.getL("sySynapses").listToModel( ui.getSynapses() );
				//w.getL("sySFDLabels").listToModel( ui.getSynPSGs() );
				//w.getL("syLearnLabels").listToModel( ui.getSynapses() );
				
				return false;
			}
		});
			
		setEntries( w, ui );
		
		modifyHandlers( w, ui );
		
	}
	
	public void setEntries(final WidgetReferences w, final UIControllerNCS ui) throws FileNotFoundException {
	
		// Entries are set depending on synapse selected
		TreeView synView = w.getL("sySynapses").getView();		
		
		// Connect for getting selected row in tree view		
		rs1 = synView.getSelection();
		rs1.connect(new Changed() {
			
			@Override
			public void onChanged(TreeSelection arg0) {	
			
				// Get selected string
				if( rs1.getSelected() != null ) {
					
					// Get selected column
					selectedText = w.getL("sySynapses").getModel().getValue(rs1.getSelected(), w.getL("sySynapses").getHeader());
					
					// Get current synapse based on selected synapse 
					try {
						 currentSynapse = ui.getSynapseByType(selectedText);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}									
				
					// Set everything else to current column 	
					// TODO - set SDF Label and Learn Label
					((Entry) w.getW("syType")).setText(currentSynapse.type);
					// TODO - All other things don't work o.o 
					//((Entry) w.getW("sySYN")).setText(currentSynapse.synPSG.type);
				    //((Entry) w.getW("syHStart")).setText(currentSynapse.hebbStart.toString());
				    //((Entry) w.getW("syHEnd")).setText(currentSynapse.hebbEnd.toString());
					//((Entry) w.getW("syAbMean")).setText(currentSynapse.absoluteUse.mean.toString());
					//((Entry) w.getW("syAbStd")).setText(currentSynapse.absoluteUse.stdev.toString());
					//((Entry) w.getW("syMCMean")).setText(currentSynapse.maxConduct.mean.toString());
					//((Entry) w.getW("syMCStd")).setText(currentSynapse.maxConduct.stdev.toString());
					//((Entry) w.getW("syDMin")).setText(currentSynapse.delay.mean.toString());
					//((Entry) w.getW("syDMax")).setText(currentSynapse.delay.stdev.toString());					
				}							
			}
		});
		
		// Entries are set depending on SFD Label selected
		TreeView sfdView = w.getL("sySFDLabels").getView();		
		
		// Connect for getting selected row in tree view		
		rs2 = sfdView.getSelection();
		rs2.connect(new Changed() {
			
			@Override
			public void onChanged(TreeSelection arg0) {	
			
				// Get selected string
				if( rs2.getSelected() != null ) {
					
					// Get selected column
					selectedText = w.getL("sySFDLabels").getModel().getValue(rs2.getSelected(), w.getL("sySFDLabels").getHeader());
					
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
		TreeView learnView = w.getL("syLearnLabels").getView();		
		
		// Connect for getting selected row in tree view		
		rs3 = learnView.getSelection();
		rs3.connect(new Changed() {
			
			@Override
			public void onChanged(TreeSelection arg0) {	
			
				// Get selected string
				if( rs3.getSelected() != null ) {
					
					// Get selected column
					selectedText = w.getL("syLearnLabels").getModel().getValue(rs3.getSelected(), w.getL("syLearnLabels").getHeader());
					
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
	
	public void modifyHandlers(final WidgetReferences w, final UIControllerNCS ui) throws FileNotFoundException {		
		
		// Connect for adding Synapse
		((Button) w.getW("syAddSynapse") ).connect( new Clicked() {
			
			@Override
			public void onClicked(Button arg0) {
				
				w.getL("sySynapses").addData( "new synapse");
				
			}
		});		
		
		// Connect for removing Synapse
		((Button) w.getW("syRemSynapse") ).connect( new Clicked() {
			
			@Override
			public void onClicked(Button arg0) {
			
				w.getL("sySynapses").removeData( );
				
			}
		});	
		
		// Connect for adding SFD Label
		((Button) w.getW("syAddSFD") ).connect( new Clicked() {
			
			@Override
			public void onClicked(Button arg0) {
				
				w.getL("sySFDLabels").addData( "new sfd label" );
			}
		});
		
		// Connect for removing a SFD label
		((Button) w.getW("syRemSFD") ).connect(new Clicked() {
			
			@Override
			public void onClicked(Button arg0) {
				
				w.getL("sySFDLabels").removeData();
				
			}
		});
		
		// Connect for adding a Learn Label
		((Button) w.getW("syAddLearn") ).connect( new Clicked() {
			
			@Override
			public void onClicked(Button arg0) {
				
				w.getL("syLearnLabels").addData( "new learn label" );
				
			}
		});
		
		// Connect for removing a Learn Label
		((Button) w.getW("syRemLearn") ).connect( new Clicked() {
			
			@Override
			public void onClicked(Button arg0) {
				
				w.getL("syLearnLabels").removeData();
				
			}
		});
	}

}
