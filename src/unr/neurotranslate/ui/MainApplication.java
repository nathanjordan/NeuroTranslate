package unr.neurotranslate.ui;

import java.io.File;

import org.gnome.gdk.Event;
import org.gnome.gtk.Alignment;
import org.gnome.gtk.FileChooserAction;
import org.gnome.gtk.FileChooserDialog;
import org.gnome.gtk.FileFilter;
import org.gnome.gtk.Gtk;
import org.gnome.gtk.HButtonBox;
import org.gnome.gtk.Label;
import org.gnome.gtk.Menu;
import org.gnome.gtk.MenuBar;
import org.gnome.gtk.MenuItem;
import org.gnome.gtk.Notebook;
import org.gnome.gtk.ResponseType;
import org.gnome.gtk.Statusbar;
import org.gnome.gtk.ToggleButton;
import org.gnome.gtk.VBox;
import org.gnome.gtk.Widget;
import org.gnome.gtk.Window;
import org.gnome.gtk.WindowPosition;
import org.gnome.gtk.ToggleButton.Toggled;


/**
 * The MainApplication class is responsible for creating the menu-bar, tab interface, and handling importing and exporting of files. A Table layout is used in order to hold all the widgets used
 * in this tab. 
 * @author nitish/kim
 */
public class MainApplication extends Window {
	
	// Vertical box containers	
	private VBox ncsBox;	
	private VBox brainBox;
	private VBox anatomyBox;
	private VBox connectBox;
	private VBox synapseBox;
	private VBox stimulusBox;
	private VBox reportBox;
	private VBox morphologyBox;	
	private VBox channelsBox;
	private VBox networksBox;
	
	// Window stuff
	private static int windowHeight = 300;
	private static int windowWidth = 400;
	private ToggleButton ncsTButton;
	private ToggleButton nmlTButton;
	
	// Tabbed viewing variables
	private Notebook ncsTabs;	
	private Notebook nmlTabs;
	
	// Imported File
	private String importedFile;
	private File temp;
	private Statusbar statusBar;	
	
	// NeuroTranslate constructor
	public MainApplication() {
		
		// Set title of window
		setTitle( "NeuroTranslate" );
		
		setResizable(false);				
		
		// Initialize GUI
		initUI();							
				
		// Set initial parameters of window
		setDefaultSize( windowWidth, windowHeight );		
        setPosition( WindowPosition.CENTER );       
        
        // Show all window components
        showAll();
        
        // Hide one set of tabs initially            
        nmlTabs.hide();	
         
    	// Handle quitting program
		connect(new Window.DeleteEvent() {
            public boolean onDeleteEvent(Widget source, Event event) {
                Gtk.mainQuit();                
                return false;
            }
        });
		
	}	
	
	// Set up main window for NeuroTranslate
	public void initUI() {
		
		// Initialize vertical box containers. Each tab is a VBox
		ncsBox = new VBox( false, 0 );		
		brainBox = new VBox( false, 0 );		
		anatomyBox = new VBox( false, 5 );
		connectBox = new VBox( false, 0 );
		synapseBox = new VBox( false, 0 );
		stimulusBox = new VBox( false, 0 );
		reportBox = new VBox( false, 0 );		
		morphologyBox = new VBox( false, 0 );
		channelsBox = new VBox( false, 0 );
		networksBox = new VBox( false, 0 );
		
		// Set menu-bar
        setMenu();  
        	
		// Create tabbed interface for NCS and NeuroML view
		ncsTabs = new Notebook();		
		nmlTabs = new Notebook();			
		
		// Create ncs view tabs
		ncsTabs.appendPage( brainBox, new Label( "Brain" )	);
		ncsTabs.appendPage( anatomyBox, new Label( "Anatomy" ) );
		ncsTabs.appendPage( connectBox, new Label( "Connect" ) );
		ncsTabs.appendPage( synapseBox, new Label( "Synapse" ) );
		ncsTabs.appendPage( stimulusBox, new Label( "Stimulus" ) );
		ncsTabs.appendPage( reportBox, new Label( "Reports" ) );		
		
		// Create neuroml view tabs
		nmlTabs.appendPage( morphologyBox, new Label( "Morphology" ) );
		nmlTabs.appendPage( channelsBox, new Label( "Channels" ) );
		nmlTabs.appendPage( networksBox, new Label( "Networks" ) );
		
		// Add tabs to top level vbox container 
		ncsBox.packStart( ncsTabs, false, false, 0 );
		ncsBox.packStart(nmlTabs, false, false, 0 );			   
	       
		// Create Brain tab
		@SuppressWarnings("unused")
		BrainTab brainTab = new BrainTab( brainBox );
             
		// Create Anatomy tab
        @SuppressWarnings("unused")
		AnatomyTab anatomyTab = new AnatomyTab( anatomyBox );
        
        // Create Connect tab
        @SuppressWarnings("unused")
        ConnectTab connectTab = new ConnectTab( connectBox );
        
        // Create Synapse tab
        @SuppressWarnings("unused")
        SynapseTab synapseTab = new SynapseTab( synapseBox );
        
        // Create Stimulus tab
        @SuppressWarnings("unused")
        StimulusTab stimulusTab = new StimulusTab( stimulusBox );             
        
        // Create Reports tab
        @SuppressWarnings("unused")
        ReportsTab reportsTab = new ReportsTab( reportBox );        
        
        // Create Morphology tab
        @SuppressWarnings("unused")
        MorphologyTab morphTab = new MorphologyTab( morphologyBox );
        
        // Create Channels tab
        @SuppressWarnings("unused")
        ChannelTab channelsTab = new ChannelTab( channelsBox );
        
        // Create Networks tab
        @SuppressWarnings("unused")
        NetworkTab networksTab = new NetworkTab( networksBox );
        
        // Toggle buttons for view stack 
       ncsTButton = new ToggleButton( "NCS" );
       nmlTButton = new ToggleButton( "NeuroML" );
       
       // Align toggle buttons to the far right of window
       Alignment viewAlign = new  Alignment( 1.0f, 0.0f, 0.0f, 0.0f );              
       HButtonBox viewBox = new HButtonBox();
       viewBox.packStart( ncsTButton, false, false, 0 );
       viewBox.packStart( nmlTButton, false, false, 0 );
       viewAlign.add( viewBox );
       ncsBox.packEnd( viewAlign, false, false, 0 );
 
       // Set ncs view active on program start      
       ncsTButton.setActive( true );
        
       // Switch to NCS view if toggled
       ncsTButton.connect(new Toggled() {
		
		@Override
		public void onToggled(ToggleButton arg0) {
		
			// Toggle other view off
			if( arg0.getActive() ) {
				nmlTButton.setActive( false );
				ncsTabs.show();
				nmlTabs.hide();
				
			}				
		}
       });
       
       // Switch to NeuroML view if toggled
       nmlTButton.connect(new Toggled() {
		
		@Override
		public void onToggled(ToggleButton arg0) {
			
			// Toggle other view off
			if( arg0.getActive() ) {
				ncsTButton.setActive( false );
				ncsTabs.hide();
				nmlTabs.show();			
			}
		}
       });
       
        // Add top level vertical box container to window            
        add( ncsBox );       
	}	
	
	// Create and set a MenuBar
	public void setMenu() {
		
		// Initialize imported file(s)
		importedFile = new String();
		
		// Initialize a status bar for current imported file
		statusBar = new Statusbar();
		statusBar.setMessage( "No file imported..." );
		
		// Set up menu-bar
		MenuBar menuBar = new MenuBar();
		
		// menuBar contains File and Edit menus
		MenuItem fileItem = new MenuItem("File");	
	    menuBar.append(fileItem);	
	    
	    // Set up options for "File" menu
	    Menu fileMenu = new Menu();
	    MenuItem importItem = new MenuItem( "Import File");	    	    	 
	    importItem.connect(new MenuItem.Activate() {
		
	    	// Handle clicking on the Import File option	    
			@Override
			public void onActivate(MenuItem arg0) {
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
				
				// Update status bar
				statusBar.setMessage( temp.getName() );
				
				// Figure out which type of file was imported 							
				if( importedFile.endsWith( ".inc" ) ) {
										
					/*
					 * TODO - deal with NCS
					 */
					
					// Show ncs view and hide neuroml
					ncsTabs.show();
					nmlTabs.hide();
					nmlTButton.setActive( false );
					ncsTButton.setActive( true );
				}											
										
				else if( importedFile.endsWith( ".xml" ) ) {
				
					/*
					 * TODO - deal with XML
					 */
					
					// Show neuroml view and hide ncs
					nmlTabs.show();
					ncsTabs.hide();
					nmlTButton.setActive( true );
					ncsTButton.setActive( false );
				}
																						
				else {
					
					System.out.println( "Not a valid file " );
					
				}																					 	
			}				
			}
		});
	    	  
	    // TODO - Export as NCS or NeuroML
	    MenuItem exportItem = new MenuItem( "Export File");
	    exportItem.connect(new MenuItem.Activate() {
			
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

				System.out.println( saveDialog.getFilename() );				
			}
			}
		});
	    
	    // Quit option in-case people can't find the X :)
	    MenuItem quitItem = new MenuItem("_Exit");	    	       
        quitItem.connect(new MenuItem.Activate() {
            public void onActivate(MenuItem menuItem) {
                Gtk.mainQuit();
            }
        });
        
	    // Attach all options to the "File" menu
	    fileMenu.append( importItem );
	    fileMenu.append( exportItem );
	    fileMenu.append( quitItem );
	    fileItem.setSubmenu( fileMenu );	    	  
	    	    
	    // Show menu-bar
        ncsBox.packStart (menuBar, false, false, 0 );
	    
	    // Show status bar
        ncsBox.packEnd( statusBar, false, false, 0 );
	}	
	
	public static void main( String[] args ) {
		
		// Initialize GTK
		Gtk.init( args );						
		
		// Instantiate Application
		new MainApplication();			
		
		// Magic happens here
		Gtk.main();
		
	}
}
