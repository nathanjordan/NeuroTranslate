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
 * The AntCellTab class is responsible for handling all the Cell tab layout and widgets. A Table container is used in order to display the widgets. 
 * @author nitish/kim
 */
public class AntCellTab {
	
	private ArrayList<Widget> widgets;
	
	public AntCellTab( VBox box ) {
		
		// Create table layout for the tab
		Table table = new Table( 7, 3, true );

		// Set Cells labels/table
		ArrayList<Label> labels = new ArrayList<Label>();	
		labels.add( new Label("Cell:" ) ); 
		labels.add( new Label("Type:" ) ); 	
		labels.add( new Label("Compartment:" ) );			
		
		// Set alignment for labels
		for( Label item: labels ) {			
			item.setAlignment( 0.0f, 0.0f );			
		}
		
		// Set widgets for Cells
		widgets = new ArrayList<Widget>();
		widgets.add( new TextComboBox() );
		widgets.add( new Entry("") );	
		widgets.add( new Entry("") );
		widgets.add( new HBox(false, 5) );
		
		// Add/Remove cell buttons
		Button cellAdd = new Button();
		Button cellRemove = new Button();
		cellAdd.setImage( new Image(Stock.ADD, IconSize.BUTTON) );
		cellRemove.setImage( new Image(Stock.REMOVE, IconSize.BUTTON) ); 
		cellAdd.setSizeRequest( 40, 27 );
		cellRemove.setSizeRequest( 40, 27 );		
		( (Box) widgets.get(3)).packStart( cellAdd, false, false, 0 );
		( (Box) widgets.get(3)).packStart( cellRemove, false, false, 0 );	
		

		// Set Cell table labels			
		for( int i = 0, j = 0, k = 1; i < labels.size(); i++, j++, k++ ) {								
			table.attach( labels.get(i), 0, 1, j, k, AttachOptions.FILL, AttachOptions.FILL, 5, 15 );			
		}
		
		// Set Cell table data
		for( int i = 0, j = 0, k = 1; i < widgets.size() - 1; i++, j++, k++ ) {			
			table.attach( widgets.get(i), 1, 2, j, k, AttachOptions.FILL, AttachOptions.FILL, 5, 15 );			
		}
			
		// Attach Cell add/remove buttons 
		table.attach( widgets.get(3), 2, 3, 0, 1, AttachOptions.FILL, AttachOptions.FILL, 5, 15 );
		
		// Add table to the top level container
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
