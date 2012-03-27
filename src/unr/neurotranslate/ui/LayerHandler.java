package unr.neurotranslate.ui;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import org.gnome.gtk.Button;
import org.gnome.gtk.Entry;
import org.gnome.gtk.Notebook;
import org.gnome.gtk.Button.Clicked;
import org.gnome.gtk.Notebook.ChangeCurrentPage;

public class LayerHandler {

	// All array lists are for debugging
	public ArrayList<String> lShells;
	public ArrayList<String> lays;
	
	public ListEntity layerShells;
	public ListEntity layers;
	public Entry lsType;
	public Entry lsLower;
	public Entry lsUpper;
	public Entry lType;	
	
	public LayerHandler() throws FileNotFoundException {
		
		// Data sources
		lShells = new ArrayList<String>();
		lays = new ArrayList<String>();
		
		// Entries
		lsType = (Entry) GladeParseUtil.grabWidget( "entry15", "window1" );
		lsLower = (Entry) GladeParseUtil.grabWidget( "entry14", "window1" );
		lsUpper = (Entry) GladeParseUtil.grabWidget( "entry13", "window1" );
		lType = (Entry) GladeParseUtil.grabWidget( "entry17", "window1" );
		
		// Lists
		layerShells = new ListEntity( lShells, "lLayerShells", "window1" );
		layers = new ListEntity( lays, "lLayers", "window1" );		
		
		setLists();
		
		setEntries();
		
		modifyHandlers();
		
	}
	
	public void setLists() throws FileNotFoundException {
	
		
				
	}
	
	public void setEntries() throws FileNotFoundException {
	
		
	}
	
	public void modifyHandlers() throws FileNotFoundException {
	
		//Buttons for add/removing
		Button addShell = (Button) GladeParseUtil.grabWidget( "lShellAdd", "window1" );
		Button remShell = (Button) GladeParseUtil.grabWidget( "lShellRem", "window1" );
		Button addLayer = (Button) GladeParseUtil.grabWidget( "lLayAdd", "window1" );
		Button remLayer = (Button) GladeParseUtil.grabWidget( "lLayRem", "window1" );
		
		// Connect for adding a Layer Shell
		addShell.connect( new Clicked() {
			
			@Override
			public void onClicked(Button arg0) {
				
				layerShells.addData( "new layer shell");
				
			}
		});		
		
		// Connect for removing a Layer Shell
		remShell.connect( new Clicked() {
			
			@Override
			public void onClicked(Button arg0) {
			
				layerShells.removeData( );
				
			}
		});	
		
		// Connect for adding a Layer
		addLayer.connect( new Clicked() {
			
			@Override
			public void onClicked(Button arg0) {
				
				layers.addData( "new layer" );
			}
		});
		
		// Connect for removing a Layer
		remLayer.connect(new Clicked() {
			
			@Override
			public void onClicked(Button arg0) {
				
				layers.removeData();
				
			}
		});
	}
	
}
