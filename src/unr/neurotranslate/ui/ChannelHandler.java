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
import org.morphml.networkml.schema.SynapseDirection;
import org.morphml.networkml.schema.SynapseProperties;

import unr.neurotranslate.ui.controller.UIControllerNeuroML;


public class ChannelHandler {

	public String selectedText;	
	public TreeSelection rs1;
	DoubleExponentialSynapse currentSynapse;
	
	public ChannelHandler(WidgetReferences w, UIControllerNeuroML ui) {
	
		w.getW("channelScroll").connect(new Widget.ExposeEvent() {
			
			@Override
			public boolean onExposeEvent(Widget arg0, EventExpose arg1) {
			
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
						 //currentSynapse = ui.getSynapseByType(selectedText);
					} catch (Exception e) {
						
						e.printStackTrace();
					}									
				
					// Set everything else to current synapse 						
					//((Entry) w.getW("mCellName")).setText(currentCell.getName());
				}							
			}
		});
		
	}
	
	private void modifyHandlers(WidgetReferences w, UIControllerNeuroML ui) {
	
		// Adding synapses
		((Button)w.getW("chAddSyn")).connect( new Clicked() {
			
			@Override
			public void onClicked(Button arg0) {
	
				
			}
		});
		
		// Removing synapses
		((Button)w.getW("chRemSyn")).connect( new Clicked() {
			
			@Override
			public void onClicked(Button arg0) {
		
				
			}
		});
		
		// Channel Type
		((Entry) w.getW("chType")).connect(new Activate() {
			
			@Override
			public void onActivate(Entry arg0) {
				
				
			}
		});
		
		// Max Cond.
		((Entry) w.getW("chMaxCond")).connect(new Activate() {
			
			@Override
			public void onActivate(Entry arg0) {
				
				
			}
		});
		
		// Use Time
		((Entry) w.getW("chUse")).connect(new Activate() {
			
			@Override
			public void onActivate(Entry arg0) {
				
				
			}
		});
		
		// Delay Time
		((Entry) w.getW("chDelay")).connect(new Activate() {
			
			@Override
			public void onActivate(Entry arg0) {
				
				
			}
		});
		
		// Reversal Pot.
		((Entry) w.getW("chReversal")).connect(new Activate() {
			
			@Override
			public void onActivate(Entry arg0) {
				
				
			}
		});
		
	}
}
