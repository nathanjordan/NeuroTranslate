package unr.neurotranslate.ui;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import org.gnome.gdk.EventExpose;
import org.gnome.gtk.Button;
import org.gnome.gtk.Entry;
import org.gnome.gtk.Notebook;
import org.gnome.gtk.ScrolledWindow;
import org.gnome.gtk.TreeSelection;
import org.gnome.gtk.TreeView;
import org.gnome.gtk.Widget;
import org.gnome.gtk.Button.Clicked;
import org.gnome.gtk.Notebook.ChangeCurrentPage;
import org.gnome.gtk.TreeSelection.Changed;

import unr.neurotranslate.ncs.Cell;
import unr.neurotranslate.ncs.Layer;
import unr.neurotranslate.ncs.LayerShell;

public class LayerHandler {

	// All array lists are for debugging
	public ArrayList<String> lShells;
	public ArrayList<String> lays;
	public TreeSelection rs1, rs2;
	public String selectedText;
	
	public ListEntity layerShells;
	public ListEntity layers;
	public ComboEntity layShellSel;
	public Entry lsType;
	public Entry lsLower;
	public Entry lsUpper;
	public Entry lType;	
	public LayerShell currentLayerShell;
	public Layer currentLayer;
	public Cell currentCell;
	
	public LayerHandler() throws FileNotFoundException {
		
	
		ScrolledWindow c = (ScrolledWindow) GladeParseUtil.grabWidget("scrolledwindow2", "window1");
		
		c.connect(new Widget.ExposeEvent() {
			
			@Override
			public boolean onExposeEvent(Widget arg0, EventExpose arg1) {
				
				// fill out all entries/lists/combo boxes
				

				return false;
			}
		});
		
		// Data sources
		lShells = new ArrayList<String>();
		lays = new ArrayList<String>();
		selectedText = new String();
		
		// Entries
		lsType = (Entry) GladeParseUtil.grabWidget( "entry15", "window1" );
		lsLower = (Entry) GladeParseUtil.grabWidget( "entry14", "window1" );
		lsUpper = (Entry) GladeParseUtil.grabWidget( "entry13", "window1" );
		lType = (Entry) GladeParseUtil.grabWidget( "entry17", "window1" );
		
		// Lists
		layerShells = new ListEntity( "lLayerShells", "window1" );
		layers = new ListEntity( "lLayers", "window1" );		
		layShellSel = new ComboEntity( "combobox1", "window1" );
		
		setLists();
		
		setEntries();
		
		modifyHandlers();
		
	}
	
	public void setLists() throws FileNotFoundException {
	
		
				
	}
	
	public void setEntries() throws FileNotFoundException {
	
		// Entries are set depending on current layer shell selected
		TreeView layShView = layerShells.getView();		
		
		// Connect for getting selected row in tree view		
		rs1 = layShView.getSelection();
		rs1.connect(new Changed() {
			
			@Override
			public void onChanged(TreeSelection arg0) {	
			
				// Get selected string
				if( rs1.getSelected() != null ) {
					
					// Get selected layer shell
					selectedText = layerShells.getModel().getValue(rs1.getSelected(), layerShells.getHeader());

					// Get current layer shell based on selected layer shell 
					// currentLayerShell = getLayerShellByType(selectedText);									
				
					// Set everything else to current 
					/*lsType.setText(currentLayerShell.type);
					lsLower.setText(currentLayerShell.lower.toString());
					lsUpper.setText(currentLayerShell.upper.toString());*/
				}							
			}
		});

	
		// Entries are set depending on current layer selected
		TreeView layView = layers.getView();		
		
		// Connect for getting selected row in tree view		
		rs2 = layView.getSelection();
		rs2.connect(new Changed() {
			
			@Override
			public void onChanged(TreeSelection arg0) {	
			
				// Get selected string
				if( rs2.getSelected() != null ) {
					
					// Get selected layer 
					selectedText = layers.getModel().getValue(rs2.getSelected(), layers.getHeader());

					// Get current layer based on selected 
					// currentLayer = getLayerByType(selectedText);									
				
					// Set everything else to current column
					lType.setText(currentLayer.type);
				}							
			}
		});
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
