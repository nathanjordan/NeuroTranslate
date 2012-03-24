package unr.neurotranslate.ui;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import org.gnome.gtk.Button;
import org.gnome.gtk.Window;
import org.gnome.gtk.Button.Clicked;

public class ModifyPopup {

	// Reference the two ListStores for active and available 
	private static ListEntity activeModel;
	private static ListEntity availableModel;
	
	// Temporary active and available lists for manipulating inside the popup
	private static ArrayList<String> activeList;
	private static ArrayList<String> availableList;
	private static Window popupWin;
	
	// References to the buttons
	private static Button cancelButton;
	private static Button confirmButton;
	private static Button addButton;
	private static Button removeButton;
	
	
	public ModifyPopup( ) throws FileNotFoundException {

		// create button references for the modify popup
		cancelButton = (Button) GladeParseUtil.grabWidget( "modifyCancel", "window2" );
		confirmButton = (Button) GladeParseUtil.grabWidget( "modifyConfirm", "window2" );
		addButton = (Button) GladeParseUtil.grabWidget( "modifyAdd", "window2" );
		removeButton = (Button) GladeParseUtil.grabWidget( "modifyRemove", "window2" );			
		popupWin = (Window) GladeParseUtil.grabWidget( "window2", "window2" );			
		
		// Set connects for handling each button event
		// Set cancel event		
		cancelButton.connect( new Clicked() {
			
			@Override
			public void onClicked(Button arg0) {
			
				// Hide the window if user pushes cancel
				popupWin.hide();
				
			}
		} );
		
		// Set confirm event
		confirmButton.connect( new Clicked() {
			
			@Override
			public void onClicked(Button arg0) {
			
				// Need to save both the active and available lists
				
				
			}
		} );
		
		// Set add event
		addButton.connect( new Clicked() {
			
			@Override
			public void onClicked(Button arg0) {
			
				// Add selected row from available list to active list					
				activeModel.addData( availableModel.getSelected() );
				availableModel.removeData();

			}
		} );
		
		// Set remove event
		removeButton.connect( new Clicked() {
			
			@Override
			public void onClicked(Button arg0) {
				
				// Remove selected row from active list
				availableModel.addData( activeModel.getSelected() );
				activeModel.removeData();
			}
		} );
		
	}
	
	// Call this static function to build the popup
	public static void buildPopup() throws FileNotFoundException {
		
		// Create lists for each view
		activeList = new ArrayList<String>();		
		availableList = new ArrayList<String>();
		
		// Create the active and available tree views
		activeModel = new ListEntity( activeList, "activeTree", "window2" );
		availableModel = new ListEntity( availableList, "availableTree", "window2");

	}
	
	// Call this function in order to modify and update the popup
	public static void updateViews( String header, ArrayList<String> active, ArrayList<String> available ) {
		
		// Update headers based on what's passed in
		popupWin.setTitle( "Adding " + header );
		activeModel.setColumnHeader( header );
		availableModel.setColumnHeader( header );
		
		// Update tool tip on buttons (for debugging)
		addButton.setTooltipText( "Add new " + header );
		removeButton.setTooltipText( "Remove a " + header );
		
		activeList = active;
		availableList = available;
		
		// Update both active and available models with new lists passed in
		activeModel.listToModel( activeList );
		availableModel.listToModel( availableList );		
		
	}
	
}