package unr.neurotranslate.ui;

import java.util.ArrayList;

import org.gnome.gtk.Alignment;
import org.gnome.gtk.AttachOptions;
import org.gnome.gtk.Box;
import org.gnome.gtk.Button;
import org.gnome.gtk.Entry;
import org.gnome.gtk.HBox;
import org.gnome.gtk.IconSize;
import org.gnome.gtk.Image;
import org.gnome.gtk.Label;
import org.gnome.gtk.Stock;
import org.gnome.gtk.Table;
import org.gnome.gtk.TextComboBox;
import org.gnome.gtk.VBox;
import org.gnome.gtk.Widget;

/**
 * The AntLayerShellTab class is responsible for handling all the Layer Shell tab layout and widgets. A Table container is used in order to display the widgets. 
 * @author nitish/kim
 */
public class AntLayerShellTab {

	// List of all widgets used in this tab
	ArrayList<Widget> widgets;

	public AntLayerShellTab( VBox box ) {
		
		Table table = new Table( 5, 3, true );
		
		
		// Set Layer Shells labels
		ArrayList<Label> anatomyLayerShellLabels = new ArrayList<Label>();		
		anatomyLayerShellLabels.add( new Label("Layer Shell:" ) ); 
		anatomyLayerShellLabels.add( new Label("Type:" ) ); 	
		anatomyLayerShellLabels.add( new Label("Lower:" ) );
		anatomyLayerShellLabels.add( new Label("Upper:" ) );
		
		// Set alignment for labels
		for( Label item: anatomyLayerShellLabels ) {			
			item.setAlignment( 0.0f, 0.0f );			
		}		
		
		// Set layer shell widgets
		widgets = new ArrayList<Widget>();		
		widgets.add( new TextComboBox() );
		widgets.add( new Entry() );
		widgets.add( new Entry() );
		widgets.add( new Entry() );
		widgets.add( new HBox( false, 5 ) );
		
		// Set up add/remove buttons
		Button layerShellAdd = new Button();
		Button layerShellRemove = new Button();	
		layerShellAdd.setImage( new Image(Stock.ADD, IconSize.BUTTON) );
		layerShellRemove.setImage( new Image(Stock.REMOVE, IconSize.BUTTON) ); 
		layerShellAdd.setSizeRequest( 40, 27 );
		layerShellRemove.setSizeRequest( 40, 27 );		
		( (Box) widgets.get(4)).packStart( layerShellAdd, false, false, 0 );
		( (Box) widgets.get(4)).packStart( layerShellRemove, false, false, 0 );	
		
		// Set Layer Shells table labels			
		for( int i = 0, j = 0, k = 1; i < anatomyLayerShellLabels.size(); i++, j++, k++ ) {								
			table.attach( anatomyLayerShellLabels.get(i), 0, 1, j, k, AttachOptions.FILL, AttachOptions.FILL, 5, 15 );			
		}
		
		// Set widgets
		for( int i = 0, j = 0, k = 1; i < widgets.size() - 1; i++, j++, k++ ) {			
			table.attach( widgets.get(i), 1, 2, j, k, AttachOptions.FILL, AttachOptions.FILL, 5, 15 );			
		}
		
		// Attach Layer Shells add/remove buttons
		table.attach( widgets.get(4), 2, 3, 0, 1, AttachOptions.FILL, AttachOptions.FILL, 5, 15 );
		
		// Add table to VBox container for tab
		box.packStart( table, false, false, 0 );
	
		// Add save/reset buttons to anatomy tab
		Alignment balign = new  Alignment( 0.0f, 0.0f, 0.0f, 0.0f );
		Button saveB = new Button( Stock.SAVE );		
		Button resetB = new Button( "_Reset" );		
		HBox contentControl = new HBox( false, 5 );
		contentControl.packStart( saveB, false, false, 0 );
		contentControl.packStart( resetB, false, false, 5 );
		balign.add(contentControl);		
		box.packEnd( balign, false, false, 0 );
	}

}
