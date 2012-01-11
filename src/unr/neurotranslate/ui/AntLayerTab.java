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
 * The AntLayerTab class is responsible for handling all the Layer tab layout and widgets. A Table container is used in order to display the widgets. 
 * @author nitish/kim
 */
public class AntLayerTab {
	
	// List of all widgets used in this tab
	private ArrayList<Widget> widgets;
	
	public AntLayerTab( VBox box ) {
		
		// Create table layout for the tab
		Table table = new Table( 7, 3, true );
				
		// Set Layers labels/table
		ArrayList<Label> labels = new ArrayList<Label>();	
		labels.add( new Label("Layer:" ) );
		labels.add( new Label("Type:" ) ); 
		labels.add( new Label("Layer Shell:" ) ); 	
		labels.add( new Label("Cell Type:" ) );
		
		// Set alignment for labels
		for( Label item: labels ) {			
			item.setAlignment( 0.0f, 0.0f );			
		}
		
		// Set widgets for layers
		widgets = new ArrayList<Widget>();
		widgets.add( new TextComboBox() );
		widgets.add( new Entry("") );
		widgets.add( new Entry("") );
		widgets.add( new TextComboBox() );		
		widgets.add( new HBox(false, 5) );
		widgets.add( new HBox(false, 5) );
				
		// add cell quantity
		Entry cellQ = new Entry("");
		cellQ.setSizeRequest( 40, 27 );
		cellQ.setEditable( false );
		((Box) widgets.get(5)).packStart( new Label("Cell Quantity:"), false, false, 0 );
		((Box) widgets.get(5)).packStart( cellQ, false, false, 0 );		
		Button layerAdd = new Button();
		Button layerRemove = new Button();
		layerAdd.setImage( new Image(Stock.ADD, IconSize.BUTTON) );
		layerRemove.setImage( new Image(Stock.REMOVE, IconSize.BUTTON) ); 
		layerAdd.setSizeRequest( 40, 27 );
		layerRemove.setSizeRequest( 40, 27 );		
		( (Box) widgets.get(4)).packStart( layerAdd, false, false, 0 );
		( (Box) widgets.get(4)).packStart( layerRemove, false, false, 0 );	
		
		// Set Layer table labels			
		for( int i = 0, j = 0, k = 1; i < labels.size(); i++, j++, k++ ) {								
			table.attach( labels.get(i), 0, 1, j, k, AttachOptions.FILL, AttachOptions.FILL, 5, 15 );			
		}
		
		// Set Layer table data		
		for( int i = 0, j = 0, k = 1; i < widgets.size() - 2; i++, j++, k++ ) {			
			table.attach( widgets.get(i), 1, 2, j, k, AttachOptions.FILL, AttachOptions.FILL, 5, 15 );			
		}
		
		// Attach Layer add/remove buttons
		table.attach( widgets.get(4), 2, 3, 0, 1, AttachOptions.FILL, AttachOptions.FILL, 5, 15 );
		

		// Attach Layer cell quantity
		table.attach( widgets.get(5), 2, 3, 3, 4, AttachOptions.FILL, AttachOptions.FILL, 5, 15 );
			
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
