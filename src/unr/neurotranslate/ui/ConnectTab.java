package unr.neurotranslate.ui;

import java.util.ArrayList;

import org.gnome.gtk.Alignment;
import org.gnome.gtk.AttachOptions;
import org.gnome.gtk.Button;
import org.gnome.gtk.ComboBox;
import org.gnome.gtk.HBox;
import org.gnome.gtk.Label;
import org.gnome.gtk.Stock;
import org.gnome.gtk.Table;
import org.gnome.gtk.TextComboBox;
import org.gnome.gtk.VBox;
import org.gnome.gtk.Widget;

/**
 * The ConnectTab class is responsible for handling all the Connect tab layout and widgets. A Table container is used in order to display the widgets. 
 * @author nitish/kim
 */
public class ConnectTab {

	private ArrayList<Widget> widgets;
	
	public ConnectTab(VBox container ) {
		
		// Set connect labels
		ArrayList<Label> labels = new ArrayList<Label>();
		labels.add( new Label("From:") ); 
		labels.add( new Label("To:") );
		labels.add( new Label("Column") );
		labels.add( new Label("Layer") );
		labels.add( new Label("Cell") );
		labels.add( new Label("Compartment") );			
		
		// set widgets for connect tab
		widgets = new ArrayList<Widget>();
		for( int i = 0; i < 8; i++ ) {			
			widgets.add( new TextComboBox() );		
			widgets.get(i).setSizeRequest( 140, 27 );
		}
		
		// set fake data
		( (TextComboBox) widgets.get(0) ).appendText("column_1");
		( (TextComboBox) widgets.get(1) ).appendText("layer_A");
		( (TextComboBox) widgets.get(2) ).appendText("Excitatory");
		( (TextComboBox) widgets.get(3) ).appendText("s1");
		( (TextComboBox) widgets.get(4) ).appendText("column_2");
		( (TextComboBox) widgets.get(5) ).appendText("layer_B");
		( (TextComboBox) widgets.get(6) ).appendText("Excitatory");
		( (TextComboBox) widgets.get(7) ).appendText("s1");
		
		// create the connect table
		Table table = new Table( 2, 5, false );
		
		// Set connect table labels ( "From" and "To" ) 	
		for( int i = 0, j = 1, k = 2; i < 2; i++, j++, k++ ) {			
			labels.get(i).setAlignment( 0.0f, 0.0f );
			table.attach( labels.get(i), 0, 1, j, k, AttachOptions.FILL, AttachOptions.FILL, 5, 15 );			
		}		
				
		// Set connect table combo-box headers ( column, layer, cell, compartment )		
		for( int i = 2, j = 1, k = 2; i < 6; i++, j++, k++ ) {
			labels.get(i).setAlignment( 0.5f, 0.0f );
			table.attach( labels.get(i), j, k, 0, 1, AttachOptions.FILL, AttachOptions.FILL, 5, 15 );
		}
		
		// Set connect widgets ( "From" boxes )
		for( int i = 0, j = 1, k = 2; i < 4; i++, j++, k++ ) {
			((ComboBox) widgets.get(i)).setActive(0);
			table.attach( widgets.get(i), j, k, 1, 2, AttachOptions.FILL, AttachOptions.FILL, 5, 15);
		}
		
		// Set connect widgets ( "To" boxes )
		for( int i = 4, j = 1, k = 2; i < 8; i++, j++, k++ ) {
			((ComboBox) widgets.get(i)).setActive(0);
			table.attach( widgets.get(i), j, k, 2, 3, AttachOptions.FILL, AttachOptions.FILL, 5, 15 );
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
