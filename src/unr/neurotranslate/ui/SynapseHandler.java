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
import org.gnome.gtk.Entry.Activate;
import org.gnome.gtk.TreeSelection.Changed;

import unr.neurotranslate.ncs.SynFacilDepress;
import unr.neurotranslate.ncs.SynLearning;
import unr.neurotranslate.ncs.Synapse;
import unr.neurotranslate.ui.controller.UIControllerNCS;

public class SynapseHandler {

	public String selectedText;
	public TreeSelection rs1, rs2, rs3;

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
						e.printStackTrace();
					}									
				
					// Set everything else to current column 	
					// TODO - SDF Label and Learn Label are not in model currently
					((Entry) w.getW("syType")).setText(currentSynapse.type);					
					((Entry) w.getW("sySYN")).setText(currentSynapse.synPSGName);
				    //((Entry) w.getW("syHStart")).setText(currentSynapse.hebbStart.toString());
				    //((Entry) w.getW("syHEnd")).setText(currentSynapse.hebbEnd.toString());
					((Entry) w.getW("syAbMean")).setText(currentSynapse.absoluteUse.mean.toString());
					((Entry) w.getW("syAbStd")).setText(currentSynapse.absoluteUse.stdev.toString());
					((Entry) w.getW("syMCMean")).setText(currentSynapse.maxConduct.toString());					((Entry) w.getW("syDMin")).setText(currentSynapse.delay.mean.toString());
					((Entry) w.getW("syDMax")).setText(currentSynapse.delay.stdev.toString());
					((Entry) w.getW("sySRMean")).setText(currentSynapse.synReversal.mean.toString());
					((Entry) w.getW("sySRStd")).setText(currentSynapse.synReversal.stdev.toString());					
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
					// TODO - facil and depre taus are not in model 
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
				
				currentSynapse = ui.addSynapse();
				w.getL("sySynapses").addData( currentSynapse.type );
				
			}
		});		
		
		// Connect for removing Synapse
		((Button) w.getW("syRemSynapse") ).connect( new Clicked() {
			
			@Override
			public void onClicked(Button arg0) {
			
				try {
					ui.removeSynapse( w.getL("sySynapses").getSelected() );
				} catch (Exception e) { 
					e.printStackTrace();
				}
				w.getL("sySynapses").removeData();
				((Entry) w.getW("syType")).setText("");
				((Entry) w.getW("syAbMean")).setText("");
				((Entry) w.getW("syAbStd")).setText("");
				((Entry) w.getW("syMCMean")).setText("");
				((Entry) w.getW("syMCStd")).setText("");
				((Entry) w.getW("syDMin")).setText("");
				((Entry) w.getW("syDMax")).setText("");
				((Entry) w.getW("sySRMean")).setText("");
				((Entry) w.getW("sySRStd")).setText("");
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
		
		// Synapse Type
		((Entry) w.getW("syType")).connect( new Activate() {
			
			@Override
			public void onActivate(Entry arg0) {						
				w.getL("sySynapses").removeData();		
				w.getL("sySynapses").addData(arg0.getText());	
				w.getL("sySynapses").getView().grabFocus();
				currentSynapse.type = arg0.getText();		
				Utils.setColor("syType", Utils.activeGreen, w);
			}
		});
		
		// Absolute Use
		((Entry) w.getW("syAbMean")).connect( new Activate() {
			
			@Override
			public void onActivate(Entry arg0) {
				
				try {
					double d = Double.parseDouble(arg0.getText());
					currentSynapse.absoluteUse.mean = d;
					Utils.setColor("coLocX", Utils.activeGreen, w);
				} catch( NumberFormatException nfe ) {
					arg0.setText("");
					Utils.setColor("syAbMean", Utils.red, w);
				}
			}
		});
		
		((Entry) w.getW("syAbStd")).connect( new Activate() {
			
			@Override
			public void onActivate(Entry arg0) {
				
				try {
					double d = Double.parseDouble(arg0.getText());
					currentSynapse.absoluteUse.stdev = d;
					Utils.setColor("syAbStd", Utils.activeGreen, w);
				} catch( NumberFormatException nfe ) {
					arg0.setText("");
					Utils.setColor("syAbStd", Utils.red, w);
				}
			}
		});
		
		// Max Conductance
		((Entry) w.getW("syMCMean")).connect( new Activate() {
			
			@Override
			public void onActivate(Entry arg0) {
				
				try {
					double d = Double.parseDouble(arg0.getText());
					currentSynapse.maxConduct = d;
					Utils.setColor("syMCMean", Utils.activeGreen, w);
				} catch( NumberFormatException nfe ) {
					arg0.setText("");
					Utils.setColor("syMCMean", Utils.red, w);
				}
			}
		});
		
		/*((Entry) w.getW("syMCStd")).connect( new Activate() {
			
			@Override
			public void onActivate(Entry arg0) {
				
				try {
					double d = Double.parseDouble(arg0.getText());
					currentSynapse.maxConduct.stdev = d;
				} catch( NumberFormatException nfe ) {
					arg0.setText("");
				}
			}
		});*/
		
		// Delay
		((Entry) w.getW("syDMin")).connect( new Activate() {
			
			@Override
			public void onActivate(Entry arg0) {
				
				try {
					double d = Double.parseDouble(arg0.getText());
					currentSynapse.delay.mean = d;
					Utils.setColor("syDMin", Utils.activeGreen, w);
				} catch( NumberFormatException nfe ) {
					arg0.setText("");
					Utils.setColor("syDMin", Utils.red, w);
				}
			}
		});
		
		((Entry) w.getW("syDMax")).connect( new Activate() {
			
			@Override
			public void onActivate(Entry arg0) {
				
				try {
					double d = Double.parseDouble(arg0.getText());
					currentSynapse.delay.stdev = d;
					Utils.setColor("syDMax", Utils.activeGreen, w);
				} catch( NumberFormatException nfe ) {
					arg0.setText("");
					Utils.setColor("syDMax", Utils.red, w);
				}
			}
		});
		
		// SYN Reversal
		((Entry) w.getW("sySRMean")).connect( new Activate() {
			
			@Override
			public void onActivate(Entry arg0) {
				
				try {
					double d = Double.parseDouble(arg0.getText());
					currentSynapse.synReversal.mean = d;
					Utils.setColor("sySRMean", Utils.activeGreen, w);
				} catch( NumberFormatException nfe ) {
					arg0.setText("");
					Utils.setColor("sySRMean", Utils.red, w);
				}
			}
		});
		
		((Entry) w.getW("sySRStd")).connect( new Activate() {
			
			@Override
			public void onActivate(Entry arg0) {
				
				try {
					double d = Double.parseDouble(arg0.getText());
					currentSynapse.synReversal.stdev = d;
					Utils.setColor("sySRStd", Utils.activeGreen, w);
				} catch( NumberFormatException nfe ) {
					arg0.setText("");
					Utils.setColor("sySRStd", Utils.red, w);
				}
			}
		});		
	}

}
