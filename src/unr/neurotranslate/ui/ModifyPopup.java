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

		// Set connects for handling each button event
		// Set cancel event		
		((Button) w.getW("modCancel")).connect( new Clicked() {
			
			@Override
			public void onClicked(Button arg0) {
			
				// Hide the window if user pushes cancel
				//w.getL("active").refreshView();				
				w.getW("popupWin").hide();
				
			}
		} );
		
		// Set confirm event
		((Button) w.getW("modConfirm")).connect( new Clicked() {
			
			@Override
			public void onClicked(Button arg0) {
			
				// Need to save both the active and available lists
				w.getL("active").modelToList(activeList);	
				w.getL("available").modelToList(availableList);
						
				w.getL(ptr).listToModel(availableList);
				
				w.getL(ptr).setActive(activeList);
				w.getL(ptr).setAvailable(availableList);
				
				w.getW("popupWin").hide();				
				
			}
		} );
		
		// Set add event
		((Button) w.getW("modAdd")).connect( new Clicked() {
			
			@Override
			public void onClicked(Button arg0) {
			
				// Add selected row from available list to active list		
				ArrayList<String> a = new ArrayList<String>();
				w.getL("available").modelToList(a);
				
				if( a.size()-1 == 0 ) {				
					((Button)w.getW("modAdd")).setSensitive(false);
					
				}
				
				((Button)w.getW("modRem")).setSensitive(true);
				
				w.getL("active").addData( w.getL("available").getSelected() );
				w.getL("available").removeData();		
			}
		} );
		
		// Set remove event
		((Button) w.getW("modRem")).connect( new Clicked() {
			
			@Override
			public void onClicked(Button arg0) {
				
				ArrayList<String> a = new ArrayList<String>();
				w.getL("active").modelToList(a);
				
				if( a.size()-1 == 0 ) {				
					((Button)w.getW("modRem")).setSensitive(false);					
				}
				
				((Button)w.getW("modAdd")).setSensitive(true);
				
				// Remove selected row from active list
				w.getL("available").addData( w.getL("active").getSelected() );
				w.getL("active").removeData();
			}
		} );
		
	}
	
	// Call this function in order to modify and update the popup
	public static void updateViews( String header, String l, WidgetReferences w ) {

		// save widget name
		ptr = l;
		
		// Update headers based on what's passed in
		((Window) w.getW("popupWin")).setTitle( "Adding " + header );
		w.getL("active").setColumnHeader( header );
		w.getL("available").setColumnHeader( header );
		
		// Update tool tip on buttons (for debugging)
		((Button) w.getW("modAdd")).setTooltipText( "Add new " + header );
		((Button) w.getW("modRem")).setTooltipText( "Remove " + header );

		// make local changes
		activeList = new ArrayList<String>( w.getL(l).getActive() );
		availableList = new ArrayList<String>( w.getL(l).getAvailable() );

		// reset add/rem buttons if needed
		if(activeList.size() == 0) 
			((Button)w.getW("modRem")).setSensitive(false);
		else
			((Button)w.getW("modRem")).setSensitive(true);
		
		if(availableList.size() == 0)
			((Button)w.getW("modAdd")).setSensitive(false);
		else
			((Button)w.getW("modAdd")).setSensitive(true);
		
		// Update both active and available models with new lists passed in
		w.getL("active").listToModel( activeList );
		w.getL("available").listToModel( availableList );		
		
	}	
}