package unr.neurotranslate.ui;

import java.util.ArrayList;

import org.gnome.gtk.Alignment;
import org.gnome.gtk.AttachOptions;
import org.gnome.gtk.Box;
import org.gnome.gtk.Button;
import org.gnome.gtk.Entry;
import org.gnome.gtk.HBox;
import org.gnome.gtk.Label;
import org.gnome.gtk.Stock;
import org.gnome.gtk.Table;
import org.gnome.gtk.VBox;
import org.gnome.gtk.Widget;

/**
 * The Synapse class is responsible for handling all the Synapse tab layout and widgets. A Table layout is used in order to hold all the widgets used
 * in this tab. 
 * @author nitish/kim
 */
public class SynapseTab {

	// A list of all widgets used in this tab
	private ArrayList<Widget> widgets;
	
	public SynapseTab( VBox container ) {

		// Set synapse labels
		ArrayList<Label> labels = new ArrayList<Label>();
		labels.add( new Label("Exitatory") );		
		labels.add( new Label("Type:") );
		labels.add( new Label("SRF Label:") );
		labels.add( new Label("SYN PSG:") );
		labels.add( new Label("Learn Label:") );
		labels.add( new Label("Max Conduct:") );
		labels.add( new Label("SYN Reversal:") );
		labels.add( new Label("Absolute Use:") );
		labels.add( new Label("Delay:") );				
		labels.add( new Label("Inhibatory") );		
		labels.add( new Label("Type:") );
		labels.add( new Label("SRF Label:") );
		labels.add( new Label("SYN PSG:") );
		labels.add( new Label("Learn Label:") );
		labels.add( new Label("Max Conduct:") );
		labels.add( new Label("SYN Reversal:") );
		labels.add( new Label("Absolute Use:") );
		labels.add( new Label("Delay:") );	
		
		// align all labels
		for( Label item: labels ) {			
			item.setAlignment( 0.0f, 0.0f );
		}
		
		// align excitatory/inhibatory cell label to middle
		labels.get(0).setAlignment(0.0f, 0.0f);
		labels.get(labels.size() / 2).setAlignment(0.0f, 0.0f);
		
		// Set synapse widgets
		// Create excitatory widgets
		widgets = new ArrayList<Widget>();
		for( int i = 0; i < 5; i ++ ) {
			widgets.add( new Entry("0") );
		}
		
		// set fake exitatory data
		((Entry) widgets.get(0)).setText("ExcitSyn1");
		((Entry) widgets.get(1)).setText("NO_SFD");
		((Entry) widgets.get(2)).setText("PSGinhib");
		((Entry) widgets.get(3)).setText("NO_LEARN");
		((Entry) widgets.get(4)).setText("0.5");
		
		
		// HBoxes hold the x and y entries
		for( int i = 5; i < 8; i++ ) {
			widgets.add( new HBox(false, 0) );
		}
		
		// Create entries for x and y value widgets 
		ArrayList<Entry> synapseVals = new ArrayList<Entry>();
		for( int i = 0; i < 12; i++ ) {
			synapseVals.add( new Entry("0") );
			synapseVals.get(i).setSizeRequest(40, 27);
		}
		
		// Add entries to Hboxes
		for( int i = 0; i < 3; i++ ) {					
			( (Box) widgets.get(i+5) ).packStart( synapseVals.get(i*2), false, false, 0 );
			( (Box) widgets.get(i+5) ).packStart( synapseVals.get(i*2+1), false, false, 10 );			
		}
		
		// Create inhibitatory widgets
		for( int i = 8; i < 13; i ++ ) {
			widgets.add( new Entry("0") );
		}
		
		// set fake inhibatory data
		((Entry) widgets.get(8)).setText("InhibSyn1");
		((Entry) widgets.get(9)).setText("NO_SFD");
		((Entry) widgets.get(10)).setText("PSGinhib");
		((Entry) widgets.get(11)).setText("NO_LEARN");
		((Entry) widgets.get(12)).setText("0.5");
		
		
		for( int i = 13; i < 16; i++ ) {
			widgets.add( new HBox(false, 0) );
		}
		
		// Create entries for x and y value widgets 
		// Add entries to Hboxes
		for( int i = 0; i < 3; i++ ) {					
			( (Box) widgets.get(i+13) ).packStart( synapseVals.get(i*2+6), false, false, 0 );
			( (Box) widgets.get(i+13) ).packStart( synapseVals.get(i*2+1+6), false, false, 10 );			
		}
				
		// Create a synapse table ( labels are split into two columns )
		Table table = new Table( labels.size() / 2, 4, false );
				
		// Set synapse table labels ( column 1 and 3 )
		for( int i = 0, j = 0, k = 1; i < labels.size()/2; i++, j++, k++ ) {								
			table.attach( labels.get(i), 0, 1, j, k, AttachOptions.FILL, AttachOptions.FILL, 5, 15 );			
		}	
				
		for( int i = labels.size() / 2, j = 0, k = 1; i < labels.size(); i++, j++, k++ ) {								
			table.attach( labels.get(i), 2, 3, j, k, AttachOptions.FILL, AttachOptions.FILL, 35, 15 );			
		}

		// Set synapse table widgets ( column 2 and 4 )
		for( int i = 0, j = 1, k = 2; i <  widgets.size()/2; i++, j++, k++ ) {
			table.attach( widgets.get(i), 1, 2, j, k, AttachOptions.FILL, AttachOptions.FILL, 15, 15 );			
		}
		
		for( int i = widgets.size() / 2, j = 1, k = 2; i <  widgets.size(); i++, j++, k++ ) {
			table.attach( widgets.get(i), 3, 4, j, k, AttachOptions.FILL, AttachOptions.FILL, 15, 15 );			
		}
		
		// Add table to container
		container.packStart( table, false, false, 5 );

		// Add save/reset buttons
		Alignment balign = new  Alignment( 0.0f, 0.0f, 0.0f, 0.0f );
		Button saveB = new Button( Stock.SAVE );		
		Button resetB = new Button( "_Reset" );		
		HBox contentControl = new HBox( false, 5 );
		contentControl.packStart( saveB, false, false, 0 );
		contentControl.packStart( resetB, false, false, 5 );
		balign.add(contentControl);
		
		// Add buttons to top level container
		container.packEnd( balign, false, false, 0 );
		
	}
	
}
