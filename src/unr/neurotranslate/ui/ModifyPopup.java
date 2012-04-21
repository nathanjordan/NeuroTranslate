package unr.neurotranslate.ui;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import org.gnome.gtk.Button;
import org.gnome.gtk.Window;
import org.gnome.gtk.Button.Clicked;

import unr.neurotranslate.ui.controller.UIControllerNCS;

public class ModifyPopup {

	// Temporary active and available lists for manipulating inside the popup
	private static ArrayList<String> activeList;
	private static ArrayList<String> availableList;
	private static String ptr;
	
	public ModifyPopup( final WidgetReferences w ) throws FileNotFoundException {

		// Create lists for each view
		activeList = new ArrayList<String>();		
		availableList = new ArrayList<String>();		
		
		// Set connects for handling each button event
		// Set cancel event		
		((Button) w.getW("modCancel")).connect( new Clicked() {
			
			@Override
			public void onClicked(Button arg0) {
			
				// Hide the window if user pushes cancel
				w.getL("active").refreshView();				
				w.getW("popupWin").hide();
				
			}
		} );
		
		// Set confirm event
		((Button) w.getW("modConfirm")).connect( new Clicked() {
			
			@Override
			public void onClicked(Button arg0) {
			
				// Need to save both the active and available lists
				w.getL("active").modelToList(activeList);
				w.getL(ptr).listToModel(activeList);
				w.getW("popupWin").hide();				
				
			}
		} );
		
		// Set add event
		((Button) w.getW("modAdd")).connect( new Clicked() {
			
			@Override
			public void onClicked(Button arg0) {
			
				// Add selected row from available list to active list					
				w.getL("active").addData( w.getL("available").getSelected() );
				w.getL("available").removeData();

			}
		} );
		
		// Set remove event
		((Button) w.getW("modRem")).connect( new Clicked() {
			
			@Override
			public void onClicked(Button arg0) {
				
				// Remove selected row from active list
				w.getL("available").addData( w.getL("active").getSelected() );
				w.getL("active").removeData();
			}
		} );
		
	}
	
	// Call this function in order to modify and update the popup
	public static void updateViews( String header, ArrayList<String> active, ArrayList<String> available, WidgetReferences w ) {
		
		// Update headers based on what's passed in
		((Window) w.getW("popupWin")).setTitle( "Adding " + header );
		w.getL("active").setColumnHeader( header );
		w.getL("available").setColumnHeader( header );
		
		// Update tool tip on buttons (for debugging)
		((Button) w.getW("modAdd")).setTooltipText( "Add new " + header );
		((Button) w.getW("modRem")).setTooltipText( "Remove a " + header );
		
		activeList = active;
		availableList = available;
		
		// Update both active and available models with new lists passed in
		w.getL("active").listToModel( activeList );
		w.getL("available").listToModel( availableList );		
		
	}
	
	// Call this function in order to modify and update the popup
	public static void update( String header, String l, WidgetReferences w, UIControllerNCS ui ) {
		
		// Update headers based on what's passed in
		((Window) w.getW("popupWin")).setTitle( "Adding " + header );
		w.getL("active").setColumnHeader( header );
		w.getL("available").setColumnHeader( header );
		
		// Update tool tip on buttons (for debugging)
		((Button) w.getW("modAdd")).setTooltipText( "Add new " + header );
		((Button) w.getW("modRem")).setTooltipText( "Remove " + header );
				
		w.getL("available").listToModel( ui.getLayers() );		
		ptr = new String(l);
	}
}