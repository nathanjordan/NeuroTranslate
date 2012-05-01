package unr.neurotranslate.ui;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.gnome.gdk.Color;
import org.gnome.gtk.FileChooserAction;
import org.gnome.gtk.FileChooserDialog;
import org.gnome.gtk.FileFilter;
import org.gnome.gtk.MenuItem;
import org.gnome.gtk.ResponseType;
import org.gnome.gtk.StateType;
import org.gnome.gtk.Statusbar;
import org.gnome.gtk.ToggleButton;
import org.gnome.gtk.MenuItem.Activate;
import org.gnome.gtk.ToggleButton.Toggled;
import org.gnome.notify.Notification;
import org.morphml.neuroml.schema.Neuroml;

import unr.neurotranslate.conversion.FormatConverter;
import unr.neurotranslate.conversion.NCSConversionData;
import unr.neurotranslate.conversion.NeuroMLConversionData;
import unr.neurotranslate.model.Data;
import unr.neurotranslate.model.FileController;
import unr.neurotranslate.ncs.NCSData;
import unr.neurotranslate.neuromlparser.NeuroMLConverter;
import unr.neurotranslate.ui.controller.UIControllerNCS;
import unr.neurotranslate.ui.controller.UIControllerNeuroML;


public class FileHandler {

	// Class members
	private String importedFile;
	private File temp;
	static Boolean ncsState = false; 
	static Boolean nmlState = false;
	static Boolean ncsSaveState = false; 
	static Boolean nmlSaveState = false;
	
	
	// Constructor
	public FileHandler( final WidgetReferences w  ) throws FileNotFoundException {
					
		// set up default view (buttons not sensitive/nmlView hidden)
		w.getW("ncsToggle").setSensitive( false );  
		w.getW("nmlToggle").setSensitive( false );  
	    w.getW("nmlTabs").hide();
		
		// create translation dialog
		ErrorHandler errors = new ErrorHandler(w);
	    
	    // do menu init stuff
		initMenu( w, errors );		
	
		// Enable NeuroML toggle button if imported NCS
	
		buttonHandler( w, errors );
		
		
	}
	
	// Initialize file menu
	public void initMenu(final WidgetReferences w, ErrorHandler errors) {
		
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
								
						ncsSaveState = true;
						
						// load data model
						NCSData d = FileController.loadNCSFile( importedFile );
						UIControllerNCS ui = new UIControllerNCS( d );
						
						// set views
						w.getW("nmlTabs").hide();
					    w.getW("ncsTabs").show();
						
						// set button colors
						w.getW("nmlToggle").setSensitive( true );
						w.getW("ncsToggle").setSensitive( false );					
						Color activeGreen = new Color(44880, 55552, 36608 );        
					    w.getW("nmlToggle").modifyBackground(StateType.NORMAL, activeGreen );
					    w.getW("nmlToggle").modifyBackground(StateType.SELECTED, activeGreen );	
					    
						// set all handlers!
					    try {
							new NCSHandlers(w, ui);
						} catch (Exception e) {						
							e.printStackTrace();
						}

						// Update status bar
						((Statusbar) w.getW("statusbar")).setMessage( temp.getName() );
					}											
											
					else if( importedFile.endsWith( ".xml" ) ) {
											
						nmlSaveState = true;
						
						// load data model
						NeuroMLConverter n = null;
						try {
							n = new NeuroMLConverter();
						} catch (Exception e1) {						
							e1.printStackTrace();
						}
						
						Neuroml d = null;
						try {
							d = n.readNeuroML( importedFile );
						} catch (Exception e) {				
							e.printStackTrace();
						}
						
						UIControllerNeuroML ui = new UIControllerNeuroML( d );
		
						// set views
						w.getW("ncsTabs").hide();
					    w.getW("nmlTabs").show();						
						w.getW("ncsToggle").setSensitive( true );  
						w.getW("nmlToggle").setSensitive( false );						
						Color activeGreen = new Color(44880, 55552, 36608 );        
					    w.getW("ncsToggle").modifyBackground(StateType.NORMAL, activeGreen );
					    w.getW("ncsToggle").modifyBackground(StateType.SELECTED, activeGreen );
					     
					    // Set up handlers
					    new NeuroMLHandlers( w, ui );
						
						// Update status bar
						((Statusbar) w.getW("statusbar")).setMessage( temp.getName() );
					}
																							
					else {
						// Handle invalid file exception						
						
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
												
					// Write to file!
					if( ncsSaveState )
						FileController.saveNCSFile(  saveDialog.getFilename() );
					if( nmlSaveState )
						FileController.saveNeuroMLFile( saveDialog.getFilename() );
										
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
	
	public void buttonHandler( final WidgetReferences w, final ErrorHandler errors ) throws FileNotFoundException {
	
        // Set ncsToggle button connect
        ((ToggleButton)w.getW("ncsToggle")).connect( new Toggled() {			
			@Override
			public void onToggled(ToggleButton arg0) {			
				// Toggle other view off
				if( arg0.getActive() ) {
					((ToggleButton) w.getW("nmlToggle")).setActive( false );	
					ncsState = true;
					
					// Create conversion notes and temp ncs data		
					NCSConversionData ncs = new NCSConversionData();
					
					// Translate to NCS!
					try {
						ncs =  FormatConverter.convertToNCS(Data.getInstance().nml);
					} catch (IOException e1) {					
						e1.printStackTrace();
					}
					
					// Update warnings and errors
					ErrorHandler.update( ncs.getNotes(), w );
					
					// display translation dialog					
					w.getW("translateDialog").show();	

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
				   nmlState = true;
				   
				   // Grab conversion data from NeuroML to NCS!
				   NeuroMLConversionData nml = new NeuroMLConversionData();				   
				   nml = FormatConverter.convertToNeuroML(Data.getInstance().ncs);
	   
				   // Update warnings and errors
				   ErrorHandler.update( nml.getNotes(), w );
				   
				   w.getW("translateDialog").show();
				   
				   
				   //w.getW("nmlTabs").show();
				   //w.getW("ncsTabs").hide();
				   
				   // Grab conversion data from NeuroML to NCS!
				   //NeuroMLConversionData nml = new NeuroMLConversionData();				   
				   //nml = FormatConverter.convertToNeuroML(Data.getInstance().ncs);
				   			
				   
				   //errors.update( nml.getNotes(), w );

				   // Set up handlers
				   //UIControllerNeuroML ui = new UIControllerNeuroML( (Neuroml) nml.getData() );
				   //new NeuroMLHandlers(w, ui);
				}				
			}
		} );       
	}
}