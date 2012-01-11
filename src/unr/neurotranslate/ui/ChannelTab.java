package unr.neurotranslate.ui;

import java.util.ArrayList;

import org.gnome.gtk.AttachOptions;
import org.gnome.gtk.Entry;
import org.gnome.gtk.Label;
import org.gnome.gtk.Table;
import org.gnome.gtk.TextComboBoxEntry;
import org.gnome.gtk.VBox;
import org.gnome.gtk.Widget;

/**
 * The ChannelTab class is responsible for handling all the Channel tab layout and widgets. A Table container is used in order to display the widgets. 
 * @author nitish/kim
 */
public class ChannelTab {
	
	// List of all the widgets for this tab
	private ArrayList<Widget> widgets;
	
	public ChannelTab( VBox box ) {
		
		// Create table layout for Channels Tab
		Table channelTable = new Table( 6, 2, false );
	
		// Create labels				
		ArrayList<Label> labels = new ArrayList<Label>();
		labels.add( new Label("Synapse value:") );
		labels.add( new Label("Type:") );
		labels.add( new Label("Max_Cond:") );
		labels.add( new Label("Use time:") );
		labels.add( new Label("Delay time:") );
		labels.add( new Label("Reversal_Pot:") );
		
		// Create widgets	
		widgets = new ArrayList<Widget>();
		widgets.add( new TextComboBoxEntry() );
		for( int i = 0; i < 5; i++ ) {
			widgets.add( new Entry() );	
			
			if( i > 0 ) {
				widgets.get(i+1).setSizeRequest(40, 27);
			}
				
		}			
		
		widgets.get(0).setSizeRequest(140, 27);
		
		// Attach labels to table
		for( int i = 0, j = 0, k = 1; i < labels.size(); i++, j++, k++ ) {
			labels.get(i).setAlignment( 0.0f, 0.0f );
			channelTable.attach( labels.get(i), 0, 1, j, k, AttachOptions.FILL, AttachOptions.FILL, 5, 15 );
		}
		
		// Attach widgets to table	
		for( int i = 0, j = 0, k = 1; i < widgets.size(); i++, j++, k++ ) {			
			channelTable.attach( widgets.get(i), 1, 2, j, k, AttachOptions.FILL, AttachOptions.FILL, 20, 15 );
		}
				
		// Add table to main tab
		box.packStart( channelTable, false, false, 5 );
	}

}
