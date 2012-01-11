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
 * The AntColumnTab class is responsible for handling all the Column tab layout and widgets. A Table container is used in order to display the widgets. 
 * @author nitish
 */
public class AntColumnTab {
	
	
	private ArrayList<Widget> anatomyColumnData;
	
	private Boolean changing;
	
	public AntColumnTab( VBox box ) {
		
		// Create table layout for the tab
		Table columnsTable = new Table( 7, 3, true );
		

		// Set Columns labels/table
		ArrayList<Label >anatomyColumnLabels = new ArrayList<Label>();				
		anatomyColumnLabels.add( new Label("Column:") ); 
		anatomyColumnLabels.add( new Label("Type:") ); 	
		anatomyColumnLabels.add( new Label("Column Shell:") );
		anatomyColumnLabels.add( new Label("Layer Type:") );	
		
		// Set alignment for labels
		for( Label item: anatomyColumnLabels ) {			
			item.setAlignment( 0.0f, 0.0f );			
		}		
		
		// Set column widgets
		anatomyColumnData = new ArrayList<Widget>();
		anatomyColumnData.add( new TextComboBox() );
		anatomyColumnData.add( new Entry() );	
		anatomyColumnData.add( new Entry() );
		anatomyColumnData.add( new TextComboBox() );
		anatomyColumnData.add( new HBox( false, 5 ) );
		
		// Set up add/remove buttons
		final Button columnAdd = new Button();
		final Button columnRemove = new Button();	
		columnAdd.setImage( new Image(Stock.ADD, IconSize.BUTTON) );
		columnRemove.setImage( new Image(Stock.REMOVE, IconSize.BUTTON) ); 		
		columnAdd.setSizeRequest(40, 27);
		columnRemove.setSizeRequest(40, 27);
		
		( (Box) anatomyColumnData.get(4) ).packStart( columnAdd, false, false, 0 );
		( (Box) anatomyColumnData.get(4) ).packStart( columnRemove, false, false, 0 );
		
		// Set fake data from column model
		final ArrayList<column> columnList = new ArrayList<column>();
		columnList.add( new column( "column_1", "column_shell" ) );
		columnList.add( new column( "column_2", "column_shell") );
		
		// Set column combo-box names 
		for( int i = 0; i < columnList.size(); i++ ) {
			((TextComboBox) anatomyColumnData.get(0)).appendText( columnList.get(i).getType() );
		}
				
		// Set combo-box default to first one
		( (TextComboBox) anatomyColumnData.get(0) ).setActive(0);	
		( (Entry) anatomyColumnData.get(1) ).setText(columnList.get(0).getType() );
		( (Entry) anatomyColumnData.get(2) ).setText(columnList.get(0).getColumnShell() );			
		
		// Set changing variable to false initially. 		
		changing = false;
		
		// Update corresponding Column data as combo-box changes
		((TextComboBox) anatomyColumnData.get(0)).connect( new Changed() {
			
			@Override
			public void onChanged(ComboBox arg0) {
				
				columnRemove.setSensitive( true );						
				
				// Get active combo-box entry			
				int index = ((TextComboBox) arg0).getActive();																	
				
				// Check if items in the combo-box are being removed. Combo-box items should NOT be updated while removing!!!
				if( !changing ) {
					
					// Make sure list isn't empty to avoid exceptions
					if( columnList.size() > 0 ) {
						
						// Update type
						( (Entry) anatomyColumnData.get(1)).setText(columnList.get(index).getType());
						
						// Update column shell
						( (Entry) anatomyColumnData.get(2)).setText(columnList.get(index).getColumnShell());
						
					}	
				}
			}
						
		});
				
		// Event handlers for add button
		columnAdd.connect( new Clicked() {
			
			@Override
			public void onClicked(Button arg0) {
				
				// Create new default column
				columnList.add( new column( "new_column", "column_a" ) );
				
				// Set combo-box to new default column shell
				( (TextComboBox) anatomyColumnData.get(0)).appendText( columnList.get(columnList.size() - 1).getType() );			
				
				// Set active item to the newly created column shell
				( (TextComboBox) anatomyColumnData.get(0)).setActive( columnList.size() - 1 );
				
			}
		});
		
		// Event handlers for remove button
		columnRemove.connect( new Clicked() {
			
			@Override
			public void onClicked(Button arg0) {
				
				int listSize = columnList.size();
				
				// Make sure list actually has an object to remove
				if( listSize > 0 ) {					
					
					int removeIndex = ((TextComboBox) anatomyColumnData.get(0)).getActive();
					
					// Remove selected item from list
					changing = true;
					columnList.remove( removeIndex );
					
					// erase type
					( (Entry) anatomyColumnData.get(1) ).setText("");
					
					// erase width
					( (Entry) anatomyColumnData.get(2) ).setText("");								
							
					// Remove selected item from combo box
					((TextComboBox) anatomyColumnData.get(0)).removeText( removeIndex );														
					changing = false;									
				}	
				
				// Set remove button to not sensitive to prevent silliness
				arg0.setSensitive( false );				
			
			}
		});
		
		// Set Column table labels			
		for( int i = 0, j = 0, k = 1; i < anatomyColumnLabels.size(); i++, j++, k++ ) {								
			columnsTable.attach( anatomyColumnLabels.get(i), 0, 1, j, k, AttachOptions.FILL, AttachOptions.FILL, 5, 15 );			
		}
		
		// Set Column table data
		for( int i = 0, j = 0, k = 1; i < anatomyColumnData.size() - 1; i++, j++, k++ ) {			
			columnsTable.attach( anatomyColumnData.get(i), 1, 2, j, k, AttachOptions.FILL, AttachOptions.FILL, 5, 15 );			
		}
		
		// Attach Column add/remove buttons	
		columnsTable.attach( anatomyColumnData.get(4), 2, 3, 0, 1, AttachOptions.FILL, AttachOptions.FILL, 5, 15 );	
		
		// add table to vbox container 
		box.packStart( columnsTable, false, false, 0 );
		
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
