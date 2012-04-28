package unr.neurotranslate.ui;
import org.gnome.gdk.EventExpose;
import org.gnome.gtk.Button;
import org.gnome.gtk.Entry;
import org.gnome.gtk.TreeSelection;
import org.gnome.gtk.TreeView;
import org.gnome.gtk.Widget;
import org.gnome.gtk.Button.Clicked;
import org.gnome.gtk.Entry.Activate;
import org.gnome.gtk.TreeSelection.Changed;
import org.morphml.channelml.schema.ChannelmlType;
import org.morphml.channelml.schema.DoubleExponentialSynapse;
import org.morphml.channelml.schema.SynapseType;
import org.morphml.metadata.schema.Point;
import org.morphml.networkml.schema.SynapseDirection;
import org.morphml.networkml.schema.SynapseProperties;

import unr.neurotranslate.ui.controller.UIControllerNeuroML;


public class ChannelHandler {

	public String selectedText;	
	public TreeSelection rs1;
	SynapseType currentSynapse;
	
	public ChannelHandler(final WidgetReferences w, final UIControllerNeuroML ui) {
	
		w.getW("channelScroll").connect(new Widget.ExposeEvent() {
			
			@Override
			public boolean onExposeEvent(Widget arg0, EventExpose arg1) {
			
				w.getL("chSynapses").listToModel( ui.getSynapses() );
				
				return false;
			}
		});

		setEntries( w, ui );
		
		modifyHandlers( w, ui );
	}

	private void setEntries( final WidgetReferences w, final UIControllerNeuroML ui) {
	
		// Entries are set depending on current synapse selected
		TreeView synapseView = w.getL("chSynapses").getView();		
		
		// Connect for getting selected row in tree view		
		rs1 = synapseView.getSelection();						
		rs1.connect(new Changed() {
			
			@Override
			public void onChanged(TreeSelection arg0) {	
			
				// Get selected string
				if( rs1.getSelected() != null ) {
					
					// Get selected column
					selectedText = w.getL("chSynapses").getModel().getValue(rs1.getSelected(), w.getL("chSynapses").getHeader());
					
					// Get current synapse based on selected synapse 
					try {
						 currentSynapse = ui.getSynapsebyName(selectedText);
					} catch (Exception e) {
						
						e.printStackTrace();
					}									
				
					// Set everything else to current synapse 						
					((Entry) w.getW("chType")).setText(currentSynapse.getName());
					((Entry) w.getW("chMaxCond")).setText( Double.toString( currentSynapse.getDoubExpSyn().getMaxConductance()) );
					((Entry) w.getW("chUse")).setText( Double.toString( currentSynapse.getDoubExpSyn().getRiseTime()) );
					((Entry) w.getW("chDelay")).setText( Double.toString( currentSynapse.getDoubExpSyn().getDecayTime()) );
					((Entry) w.getW("chReversal")).setText( Double.toString( currentSynapse.getDoubExpSyn().getReversalPotential()) );
				}							
			}
		});		
	}
	
	private void modifyHandlers(final WidgetReferences w, final UIControllerNeuroML ui) {
	
		// Adding synapses
		((Button)w.getW("chAddSyn")).connect( new Clicked() {
			
			@Override
			public void onClicked(Button arg0) {
	
				currentSynapse = ui.addSynapse();
				w.getL("chSynapses").addData( currentSynapse.getName() );								
			}
		});
		
		// Removing synapses
		((Button)w.getW("chRemSyn")).connect( new Clicked() {
			
			@Override
			public void onClicked(Button arg0) {
				
				try {
					ui.removeSynapse(w.getL("chSynapses").getSelected() );					
				} catch (Exception e) {					
					e.printStackTrace();
				}
				
				w.getL("chSynapses").removeData();			
				((Entry) w.getW("chType")).setText("");
				((Entry) w.getW("chMaxCond")).setText("");
				((Entry) w.getW("chUse")).setText("");
				((Entry) w.getW("chDelay")).setText("");
				((Entry) w.getW("chReversal")).setText("");				
			}
		});
		
		// Channel Type
		((Entry) w.getW("chType")).connect(new Activate() {
			
			@Override
			public void onActivate(Entry arg0) {
				
				w.getL("chSynapses").removeData();		
				w.getL("chSynapses").addData(arg0.getText());	
				w.getL("chSynapses").getView().grabFocus();
				currentSynapse.setName(arg0.getText());	
			}
		});
		
		// Max Cond.
		((Entry) w.getW("chMaxCond")).connect(new Activate() {
			
			@Override
			public void onActivate(Entry arg0) {
				
				try {
					double d = Double.parseDouble(arg0.getText());					
						currentSynapse.getDoubExpSyn().setMaxConductance(d);
				} catch( NumberFormatException nfe ) {
					arg0.setText("");
				}
			}
		});
		
		// Rise Time
		((Entry) w.getW("chUse")).connect(new Activate() {
			
			@Override
			public void onActivate(Entry arg0) {
				
				try {
					double d = Double.parseDouble(arg0.getText());					
						currentSynapse.getDoubExpSyn().setRiseTime(d);
				} catch( NumberFormatException nfe ) {
					arg0.setText("");
				}			
			}
		});
		
		// Delay Time
		((Entry) w.getW("chDelay")).connect(new Activate() {
			
			@Override
			public void onActivate(Entry arg0) {
				
				try {
					double d = Double.parseDouble(arg0.getText());					
						currentSynapse.getDoubExpSyn().setDecayTime(d);
				} catch( NumberFormatException nfe ) {
					arg0.setText("");
				}
				
			}
		});
		
		// Reversal Pot.
		((Entry) w.getW("chReversal")).connect(new Activate() {
			
			@Override
			public void onActivate(Entry arg0) {
				
				try {
					double d = Double.parseDouble(arg0.getText());					
						currentSynapse.getDoubExpSyn().setReversalPotential(d);
				} catch( NumberFormatException nfe ) {
					arg0.setText("");
				}
			}
		});
		
	}
}
