package unr.neurotranslate.ui;

import java.util.ArrayList;

import org.gnome.gtk.Alignment;
import org.gnome.gtk.AttachOptions;
import org.gnome.gtk.Box;
import org.gnome.gtk.Button;
import org.gnome.gtk.ComboBox;
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
import org.gnome.gtk.Button.Clicked;
import org.gnome.gtk.ComboBox.Changed;

/**
 * The AntColumnShellTab class is responsible for handling all the Column Shell tab layout and widgets. A Table container is used in order to display the widgets.
 * @author nitish/kim
 */
public class AntColumnShellTab {

	// Bool value used for updating column shell combo-box
	private Boolean changing;
	
	// List of all widgets under the column shell tab
	private ArrayList<Widget> widgets;
	
	// List of the current column shells present in the model
	private ArrayList<columnShell> columnShellList;
	
	public AntColumnShellTab( VBox box ) {
				
		// Create table for layout
		Table table = new Table( 4, 3, true );
		
		// Set Column Shells labels/table
		ArrayList<Label> labels = new ArrayList<Label>();
		labels.add( new Label("Column Shells:" ) );	
		labels.add( new Label("Type:" ) ); 	
		labels.add( new Label("Width:" ) );
		labels.add( new Label("Height:" ) );
		labels.add( new Label("Location:" ) );		
				
		// Set alignment for all labels 
		for( Label item: labels ) {			
			item.setAlignment( 0.0f, 0.0f );			
		}				
	
		// Set widgets and data for Column Shells tab			
		widgets = new ArrayList<Widget>();
		widgets.add( new TextComboBox() );
		widgets.add( new Entry() );
		widgets.add( new Entry() );
		widgets.add( new Entry() );
		widgets.add( new Entry() );
		widgets.add( new HBox(false, 5) );
		
		// Create buttons for adding and removing column shells
		// These two buttons are placed into a HBox container
		final Button cShellAdd = new Button();
		final Button cShellRemove = new Button();
		cShellAdd.setSizeRequest( 40, 27 );
		cShellRemove.setSizeRequest( 40, 27 );		
		cShellAdd.setTooltipMarkup( "Add new Column Shell" );
		cShellRemove.setTooltipMarkup( "Remove selected Column Shell" );					
		( (Box) widgets.get(5) ).packStart( cShellAdd, false, false, 0 );
		( (Box) widgets.get(5) ).packStart( cShellRemove, false, false, 0 );						
		Image addImage = new Image();
		Image removeImage = new Image();		
		addImage.setImage( Stock.ADD, IconSize.BUTTON );
		removeImage.setImage( Stock.REMOVE, IconSize.BUTTON );		
		cShellAdd.setImage( addImage );
		cShellRemove.setImage( removeImage );
		
		// Set data for columnShell combo-box from fake model
		columnShellList = new ArrayList<columnShell>();		
		columnShellList.add( new columnShell( "column_shell_a", "300", "800", "(0, 800)") );
		columnShellList.add( new columnShell( "column_shell_b", "4", "5", "(2, 3)") );
		columnShellList.add( new columnShell( "column_shell_c", "7", "2", "(4, 5)") );			
		
		// Set column shell combo-box names 
		for( int i = 0; i < columnShellList.size(); i++ ) {
			((TextComboBox) widgets.get(0)).appendText( columnShellList.get(i).getType() );
		}
				
		// Set combo-box default to first one
		( (TextComboBox) widgets.get(0) ).setActive(0);	
		( (Entry) widgets.get(1) ).setText(columnShellList.get(0).getType());
		( (Entry) widgets.get(2) ).setText(columnShellList.get(0).getWidth());
		( (Entry) widgets.get(3) ).setText(columnShellList.get(0).getHeight());
		( (Entry) widgets.get(4) ).setText(columnShellList.get(0).getLocation());				
			
		// Set Column Shell table labels 	
		for( int i = 0, j = 0, k = 1; i < labels.size(); i++, j++, k++ ) {								
			table.attach( labels.get(i), 0, 1, j, k, AttachOptions.FILL, AttachOptions.FILL, 5, 15 );			
		}
			
		// Set widgets
		for( int i = 0, j = 0, k = 1; i < widgets.size() - 1; i++, j++, k++ ) {			
			table.attach( widgets.get(i), 1, 2, j, k, AttachOptions.FILL, AttachOptions.FILL, 5, 15 );			
		}
		
		// Attach Column Shell add/remove buttons
		table.attach( widgets.get(5), 2, 3, 0, 1, AttachOptions.FILL, AttachOptions.FILL, 5, 15 );			
		
		// Add table to VBox container
		box.packStart( table, false, false, 0 );

		// Set changing variable to false initially. 		
		changing = false;
				
		// Update corresponding Column Shell data as combo-box changes
		((TextComboBox) widgets.get(0)).connect(new Changed() {
			
			@Override
			public void onChanged( ComboBox arg0 ) {
				
				// Enable delete button
				cShellRemove.setSensitive(true);
				
				// Get active combo-box entry			
				int index = ((TextComboBox) arg0).getActive();																	
				
				// Check if items in the combo-box are being removed. Combo-box items should NOT be updated while removing!!!
				if( !changing ) {
					
					// Make sure list isn't empty to avoid exceptions
					if( columnShellList.size() > 0 ) {
						
						// Update type
						( (Entry) widgets.get(1)).setText(columnShellList.get(index).getType());
						
						// Update width
						( (Entry) widgets.get(2)).setText(columnShellList.get(index).getWidth());
						
						// Update height						
						( (Entry) widgets.get(3)).setText(columnShellList.get(index).getHeight());
						
						// Update location
						( (Entry) widgets.get(4)).setText(columnShellList.get(index).getLocation());
					
					}	
				}
			}
		});
		
		// Event handlers for add button
		cShellAdd.connect( new Clicked() {
			
			@Override
			public void onClicked( Button arg0 ) {
			
				// Create new default column shell
				columnShellList.add( new columnShell( "New ColumnShell", "0", "0", "(0, 0)") );
				
				// Set combo-box to new default column shell
				( (TextComboBox) widgets.get(0)).appendText( columnShellList.get(columnShellList.size() - 1).getType() );			
				
				// Set active item to the newly created column shell
				( (TextComboBox) widgets.get(0)).setActive( columnShellList.size() - 1 );
				
			}
		});
		
		// Event handlers for remove button
		cShellRemove.connect( new Clicked() {
			
			@Override
			public void onClicked( Button arg0 ) {
				
				int listSize = columnShellList.size();
				
				// Make sure list actually has an object to remove
				if( listSize > 0 ) {					
					
					int removeIndex = ((TextComboBox) widgets.get(0)).getActive();
					
					// Remove selected item from list
					changing = true;
					columnShellList.remove( removeIndex );
					
					// erase type
					( (Entry) widgets.get(1) ).setText("");
					
					// erase width
					( (Entry) widgets.get(2) ).setText("");
					
					// erase height						
					( (Entry) widgets.get(3) ).setText("");
					
					// erase location
					( (Entry) widgets.get(4) ).setText("");									
							
					// Remove selected item from combo box
					((TextComboBox) widgets.get(0)).removeText( removeIndex );														
					changing = false;									
				}	
				
				// Set remove button to not sensitive to prevent silliness
				arg0.setSensitive( false );				
			}
		});	
		
		// Add save/reset buttons to anatomy tab
		Alignment balign = new  Alignment( 0.0f, 0.0f, 0.0f, 0.0f );
		Button saveB = new Button( Stock.SAVE );		
		Button resetB = new Button( "_Reset" );		
		HBox contentControl = new HBox( false, 5 );
		contentControl.packStart( saveB, false, false, 0 );
		contentControl.packStart( resetB, false, false, 5 );
		balign.add(contentControl);		
		box.packEnd( balign, false, false, 0 );
		
		
		// Update column shell list if user chooses to save data
		saveB.connect( new Clicked() {
			
			@Override
			public void onClicked(Button arg0) {				
				
				// Get index of selected column shell
				int index = ((ComboBox) widgets.get(0)).getActive();
				
				// Re-populate all column shell list with new data 
				columnShellList.get(index).setType( ((Entry) widgets.get(1)).getText() );
				columnShellList.get(index).setHeight( ((Entry) widgets.get(2)).getText() );
				columnShellList.get(index).setWidth( ((Entry) widgets.get(3)).getText() );
				columnShellList.get(index).setLocation( ((Entry) widgets.get(4)).getText() );								
			}
		});
		
	}
}
