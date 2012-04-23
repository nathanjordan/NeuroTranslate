package unr.neurotranslate.ui;
import java.io.File;
import java.io.FileNotFoundException;

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

import unr.neurotranslate.model.Data;
import unr.neurotranslate.model.FileController;
import unr.neurotranslate.ui.controller.UIControllerNCS;
import unr.neurotranslate.ui.controller.UIControllerNeuroML;


public class FileHandler {

	// Class members
	private String importedFile;
	private File temp;
	
	// Constructor
	public FileHandler( final WidgetReferences w  ) throws FileNotFoundException {
					
		// set up default view (buttons not sensitive/nmlView hidden)
		w.getW("ncsToggle").setSensitive( false );  
		w.getW("nmlToggle").setSensitive( false );  
	    w.getW("nmlTabs").hide();
		
		initMenu( w);
		//buttonHandler( w );
		
	}
	
	// Initialize file menu
	public void initMenu(final WidgetReferences w) {
		
		// Set default file imported message 
		((Statusbar) w.getW("statusbar")).setMessage( "No file imported..." );
		
		// Set import handler
		((MenuItem)w.getW("open")).connect( new Activate() {
			
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
				ncsFiles.addPattern( "*.in" );				 				 				
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
					if( importedFile.endsWith( ".in" ) ) {
							
						// load data model
						UIControllerNCS ui = new UIControllerNCS();
						
						// set views
						w.getW("nmlTabs").hide();
					    w.getW("ncsTabs").show();
					     
						// Enable NeuroML toggle button if imported NCS
						try {
							buttonHandler( w );
						} catch (FileNotFoundException e1) {							
							e1.printStackTrace();
						}
						
						// set button colors
						w.getW("nmlToggle").setSensitive( true );  
						Color activeGreen = new Color(44880, 55552, 36608 );        
					    w.getW("nmlToggle").modifyBackground(StateType.NORMAL, activeGreen );
					    w.getW("nmlToggle").modifyBackground(StateType.SELECTED, activeGreen );	
					    
						// set all handlers!					    
						try {
							new BrainHandler(w, ui);
						} catch (FileNotFoundException e) {						
							e.printStackTrace();
						}  
				        try {
							new ColumnHandler(w, ui);
						} catch (Exception e) {							
							e.printStackTrace();
						}
				        try {
							new LayerHandler(w, ui);
						} catch (FileNotFoundException e) {				
							e.printStackTrace();
						}
				        try {
							new CellHandler(w, ui);
						} catch (FileNotFoundException e) {					
							e.printStackTrace();
						}
				       // new ConnectionHandler(w, ui);
				        try {
							new SynapseHandler(w, ui);
						} catch (FileNotFoundException e) {						
							e.printStackTrace();
						}
				        try {
							new StimuliHandler(w, ui);
						} catch (FileNotFoundException e) {						
							e.printStackTrace();
						}
				        try {
							new ReportHandler(w, ui);
						} catch (FileNotFoundException e) {				
							e.printStackTrace();
						}
						
						// Update status bar
						((Statusbar) w.getW("statusbar")).setMessage( temp.getName() );
					}											
											
					else if( importedFile.endsWith( ".xml" ) ) {
															
						// load data model
						UIControllerNeuroML ui = new UIControllerNeuroML();
		
						// set views
						w.getW("ncsTabs").hide();
					    w.getW("nmlTabs").show();
					    
						// Enable NCS toggle button if imported NeuroML
						try {
							buttonHandler( w );
						} catch (FileNotFoundException e1) {							
							e1.printStackTrace();
						}
						
						 w.getW("ncsToggle").setSensitive( true );  
						 Color activeGreen = new Color(44880, 55552, 36608 );        
					     w.getW("ncsToggle").modifyBackground(StateType.NORMAL, activeGreen );
					     w.getW("ncsToggle").modifyBackground(StateType.SELECTED, activeGreen );
					     
					     // TODO - create NeuroML/handlers
						new MorphologyHandler( w, ui );
						new ChannelHandler( w, ui );
						// Update status bar
						((Statusbar) w.getW("statusbar")).setMessage( temp.getName() );
					}
																							
					else {
						// Handle invalid file exception
						// TODO - throw exception
						
					}
				}			
			}
		}); // End importOption handler
		
		// Set export handler
		((MenuItem)w.getW("save")).connect( new Activate() {
			
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
												
					FileController.saveNCSFile(  saveDialog.getFilename() );
					Notification notification = new Notification( "Saved to: ", saveDialog.getFilename(), "", arg0);
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
	
	public void buttonHandler( final WidgetReferences w ) throws FileNotFoundException {

		// Build translation error dialog
		new ErrorHandler();
		
		// Grab the translate dialog window to show and hide when toggle button is pushed
		final Window translateDialog = (Window) GladeParseUtil.grabWidget( "window3", "window3" );		
		
        // Set ncsToggle button connect
        ((ToggleButton)w.getW("ncsToggle")).connect( new Toggled() {			
			@Override
			public void onToggled(ToggleButton arg0) {			
				// Toggle other view off
				if( arg0.getActive() ) {
					((ToggleButton) w.getW("nmlToggle")).setActive( false );								
					w.getW("nmlTabs").hide();
					w.getW("ncsTabs").show();					
					translateDialog.show();
				}				
			}
		} );
        
        // Set nmlToggle button connect
        ((ToggleButton)w.getW("nmlToggle")).connect( new Toggled() {			
			@Override
			public void onToggled(ToggleButton arg0) {
				// Toggle other view off
				if( arg0.getActive() ) {
				   ((ToggleButton) w.getW("ncsToggle")).setActive( false );	
				   w.getW("nmlTabs").show();
				   w.getW("ncsTabs").hide();
				   translateDialog.show();
				}				
			}
		} );       
	}
}