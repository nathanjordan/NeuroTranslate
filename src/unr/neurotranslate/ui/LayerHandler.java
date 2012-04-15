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
import unr.neurotranslate.ui.controller.UIControllerNCS;

public class LayerHandler {
	
	public TreeSelection rs1, rs2;
	public String selectedText;
	public LayerShell currentLayerShell;
	public Layer currentLayer;
	public Cell currentCell;
	
	public LayerHandler(final WidgetReferences w, final UIControllerNCS ui) throws FileNotFoundException {
		
		w.getW("layerScroll").connect(new Widget.ExposeEvent() {
			
			@Override
			public boolean onExposeEvent(Widget arg0, EventExpose arg1) {
				
				// fill out all entries/lists/combo boxes
				w.getL("lLayShells").listToModel( ui.getLayerShells() );
				w.getL("lLayers").listToModel( ui.getLayers() );
				w.getC("lShellSel").listToModel(ui.getLayerShells());

				return false;
			}
		});

		setEntries(w, ui);
		
		modifyHandlers(w, ui);
		
	}
	
	public void setLists() throws FileNotFoundException {
	
		
				
	}
	
	public void setEntries(final WidgetReferences w, final UIControllerNCS ui) throws FileNotFoundException {
	
		// Entries are set depending on current layer shell selected
		TreeView layShView = w.getL("lLayShells").getView();		
		
		// Connect for getting selected row in tree view		
		rs1 = layShView.getSelection();
		rs1.connect(new Changed() {
			
			@Override
			public void onChanged(TreeSelection arg0) {	
			
				// Get selected string
				if( rs1.getSelected() != null ) {
					
					// Get selected layer shell
					selectedText = w.getL("lLayShells").getModel().getValue(rs1.getSelected(), w.getL("lLayShells").getHeader());

					// Get current layer shell based on selected layer shell 
					 try {
						currentLayerShell = ui.getLayerShellByType(selectedText);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}									
				
					// Set everything else to current 
					((Entry) w.getW("lLSType")).setText(currentLayerShell.type);
					((Entry) w.getW("lLSLower")).setText(currentLayerShell.lower.toString());
					((Entry) w.getW("lLSUpper")).setText(currentLayerShell.upper.toString());
				}							
			}
		});

	
		// Entries are set depending on current layer selected
		TreeView layView = w.getL("lLayers").getView();		
		
		// Connect for getting selected row in tree view		
		rs2 = layView.getSelection();
		rs2.connect(new Changed() {
			
			@Override
			public void onChanged(TreeSelection arg0) {	
			
				// Get selected string
				if( rs2.getSelected() != null ) {
					
					// Get selected layer 
					selectedText = w.getL("lLayers").getModel().getValue(rs2.getSelected(), w.getL("lLayers").getHeader());

					// Get current layer based on selected 
					try {
						currentLayer = ui.getLayerByType(selectedText);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}									
				
					// Set everything else to current column
					((Entry) w.getW("lLType")).setText(currentLayer.type);
				}							
			}
		});
	}
	
	public void modifyHandlers(final WidgetReferences w, final UIControllerNCS ui) throws FileNotFoundException {	
		
		// Connect for adding a Layer Shell
		((Button) w.getW("lAddLShell")).connect( new Clicked() {
			
			@Override
			public void onClicked(Button arg0) {
				
				w.getL("lLayShells").addData( "new layer shell");
				
			}
		});		
		
		// Connect for removing a Layer Shell
		((Button) w.getW("lRemLShell")).connect( new Clicked() {
			
			@Override
			public void onClicked(Button arg0) {
			
				w.getL("lLayShells").removeData( );
				
			}
		});	
		
		// Connect for adding a Layer
		((Button) w.getW("lAddLayer")).connect( new Clicked() {
			
			@Override
			public void onClicked(Button arg0) {
				
				w.getL("lLayers").addData( "new layer" );
			}
		});
		
		// Connect for removing a Layer
		((Button) w.getW("lRemLayer")).connect(new Clicked() {
			
			@Override
			public void onClicked(Button arg0) {
				
				w.getL("lLayers").removeData();
				
			}
		});
	}
	
}
