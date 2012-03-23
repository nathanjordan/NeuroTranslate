package unr.neurotranslate.ui;
import java.io.File;

import org.gnome.gdk.Color;
import org.gnome.glade.XML;
import org.gnome.gtk.FileChooserAction;
import org.gnome.gtk.FileChooserDialog;
import org.gnome.gtk.FileFilter;
import org.gnome.gtk.MenuItem;
import org.gnome.gtk.Notebook;
import org.gnome.gtk.ResponseType;
import org.gnome.gtk.StateType;
import org.gnome.gtk.Statusbar;
import org.gnome.gtk.ToggleButton;
import org.gnome.gtk.Window;
import org.gnome.gtk.MenuItem.Activate;
import org.gnome.gtk.ToggleButton.Toggled;
import org.gnome.notify.Notification;


public class FileHandler {

	// Class members
	private String importedFile;
	private File temp;
	private Statusbar statusbar;
	private MenuItem importOption;
	private MenuItem exportOption;	
	private Window mainWindow; 
	public ToggleButton ncsTButton;
	public ToggleButton nmlTButton;
	public Notebook ncsTabs;
	public Notebook nmlTabs;
	
	// Constructor
	public FileHandler() {
		
		// Get all required widgets
		statusbar = (Statusbar) GladeParseUtil.grabWidget( "statusbar", "window1" ); ;			
		importOption = (MenuItem) GladeParseUtil.grabWidget( "importOption", "window1" );
		exportOption = (MenuItem) GladeParseUtil.grabWidget( "exportOption", "window1" );
		mainWindow = (Window) GladeParseUtil.grabWidget( "window1", "window1" );
		ncsTButton = (ToggleButton) GladeParseUtil.grabWidget( "toggleNCS", "window1" );
		nmlTButton = (ToggleButton) GladeParseUtil.grabWidget( "toggleNML", "window1" );  
		ncsTabs = (Notebook) GladeParseUtil.grabWidget( "notebook1", "window1" );
		nmlTabs = (Notebook) GladeParseUtil.grabWidget( "notebook2", "window1" );
		// set file menu and toggle buttons
		initMenu();
		buttonHandler();
		
	}
	
	// Initialize file menu
	public void initMenu() {
		
		// Set default file imported message 
		statusbar.setMessage( "No file imported..." );
		
		// Set import handler
		importOption.connect( new Activate() {
			
			@Override
			public void onActivate(MenuItem arg0) {
				// Instantiate temporary file chooser dialog
				FileChooserDialog dialog;
				ResponseType response;			
				FileFilter ncsFiles, nmlFiles, allFiles;
				 
				// Instantiate the file dialog			
				dialog = new FileChooserDialog( "Open File", null, FileChooserAction.OPEN );
				
				// Set file filters for NCS or NeuroML
				ncsFiles = new FileFilter( "NCS" );
				allFiles = new FileFilter( "All Files" );
				nmlFiles = new FileFilter( "NeuroML" );
				ncsFiles.addPattern( "*.inc" );				 				 				
				allFiles.addPattern( "*" );				 				
				nmlFiles.addPattern( "*.xml" );
				dialog.addFilter( ncsFiles );
				dialog.addFilter( nmlFiles );
				dialog.addFilter( allFiles );
				
				// Run the Dialog and get the response of user
				response = dialog.run();
				dialog.hide();
				 
				// Deal with the result
				if ( response == ResponseType.OK ) {
					
					// save file imported into a file and string
					importedFile = dialog.getFilename();
					temp = new File( dialog.getFilename() );																										
														
					// Figure out which type of file was imported 							
					if( importedFile.endsWith( ".inc" ) ) {
											
					/*
					 * TODO - deal with NCS junk
					 */
						
						
						// Update status bar
						statusbar.setMessage( temp.getName() );
					}											
											
					else if( importedFile.endsWith( ".xml" ) ) {
					
					/*
					 * TODO - deal with XML junk
					 */
						
						// Update status bar
						statusbar.setMessage( temp.getName() );
					}
																							
					else {
						// Handle invalid file exception
						System.out.println( "Not a valid file " );
						
					}
				}			
			}
		}); // End importOption handler
		
		// Set export handler
		exportOption.connect( new Activate() {
			
			@Override
			public void onActivate(MenuItem arg0) {
				FileChooserDialog saveDialog;
				ResponseType response;
				
				// run the dialog
				saveDialog = new FileChooserDialog( "Save File", null, FileChooserAction.SAVE );
				response = saveDialog.run();
				saveDialog.hide();
				
				// deal with the result
				if( response == ResponseType.OK ) {
					/* TODO
					 * 
					 *  Call NCS/JAXB writer to save file
					 *  
					 */							
					Notification notification = new Notification( "Saved to: ", saveDialog.getFilename(), "", mainWindow);
					notification.setTimeout( 50 );
					notification.show();						
				}							
			}
		}); // End export option handler	
	}
	
	// Return file name 
	public String getInputFileName() {		
		return importedFile;
	}
	
	public void buttonHandler() {

		// Build translation error dialog
		new ErrorHandler();
		
		// Grab the translate dialog window to show and hide when toggle button is pushed
		final Window translateDialog = (Window) GladeParseUtil.grabWidget( "window3", "window3" );		
		
		// Set ncs view active on program start      
		ncsTButton.setSensitive( false );       
       
		// Messing around with color
        Color activeGreen = new Color(44880, 55552, 36608 );        
		nmlTButton.modifyBackground(StateType.NORMAL, activeGreen );
		nmlTButton.modifyBackground(StateType.SELECTED, activeGreen );		
		
        // Hide ncs/nml tabs for debugging
        nmlTabs.hide();
        
        // Set ncsToggle button connect
        ncsTButton.connect( new Toggled() {			
			@Override
			public void onToggled(ToggleButton arg0) {			
				// Toggle other view off
				if( arg0.getActive() ) {
					nmlTButton.setActive( false );								
					nmlTabs.hide();
					ncsTabs.show();	
					translateDialog.show();
				}				
			}
		} );
        
        // Set nmlToggle button connect
        nmlTButton.connect( new Toggled() {			
			@Override
			public void onToggled(ToggleButton arg0) {
				// Toggle other view off
				if( arg0.getActive() ) {
				   ncsTButton.setActive( false );	
				   nmlTabs.show();
				   ncsTabs.hide();
				   translateDialog.show();
				}				
			}
		} );       
	}
}