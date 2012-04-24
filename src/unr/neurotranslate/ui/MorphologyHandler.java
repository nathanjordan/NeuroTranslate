package unr.neurotranslate.ui;

import org.gnome.gdk.EventExpose;
import org.gnome.gtk.Button;
import org.gnome.gtk.Entry;
import org.gnome.gtk.TreeSelection;
import org.gnome.gtk.TreeView;
import org.gnome.gtk.Widget;
import org.gnome.gtk.Button.Clicked;
import org.gnome.gtk.Entry.Activate;
import org.gnome.gtk.TreeSelection.Changed;
import org.morphml.morphml.schema.Cable;
import org.morphml.morphml.schema.Cell;
import org.morphml.morphml.schema.Segment;

import unr.neurotranslate.ui.controller.UIControllerNeuroML;

public class MorphologyHandler {

	public String selectedText;	
	public TreeSelection rs1, rs2, rs3;
	public Cell currentCell;
	public Cable currentCable;
	public Segment currentSegment;
	
	
	public MorphologyHandler( WidgetReferences w, UIControllerNeuroML ui ) {

		w.getW("morphScroll").connect(new Widget.ExposeEvent() {
			
			@Override
			public boolean onExposeEvent(Widget arg0, EventExpose arg1) {
			
				return false;
			}
		});	
		
		setEntries( w, ui );
		
		modifyHandlers( w, ui );
		
	}

	public void setEntries(final WidgetReferences w, UIControllerNeuroML ui) {
		
		// Entries are set depending on current cell selected
		TreeView cellView = w.getL("mCells").getView();		
		
		// Connect for getting selected row in tree view		
		rs1 = cellView.getSelection();						
		rs1.connect(new Changed() {
			
			@Override
			public void onChanged(TreeSelection arg0) {	
			
				// Get selected string
				if( rs1.getSelected() != null ) {
					
					// Get selected column
					selectedText = w.getL("mCells").getModel().getValue(rs1.getSelected(), w.getL("mCells").getHeader());
					
					// Get current cell based on selected cell 
					try {
						 //currentCell = ui.getCellByType(selectedText);
					} catch (Exception e) {
						
						e.printStackTrace();
					}									
				
					// Set everything else to current cell 						
					//((Entry) w.getW("mCellName")).setText(currentCell.getName());
				}							
			}
		});

		// Entries are set depending on current cable selected
		TreeView cableView = w.getL("mCables").getView();		
		
		// Connect for getting selected row in tree view		
		rs2 = cableView.getSelection();						
		rs2.connect(new Changed() {
			
			@Override
			public void onChanged(TreeSelection arg0) {	
			
				// Get selected string
				if( rs2.getSelected() != null ) {
					
					// Get selected cable
					selectedText = w.getL("mCables").getModel().getValue(rs2.getSelected(), w.getL("mCables").getHeader());
					
					// Get current column based on selected cable 
					try {
						 //currentCable = ui.getCableByType(selectedText);
					} catch (Exception e) {
						
						e.printStackTrace();
					}									
				
					// Set everything else to current column 						
					
				}							
			}
		});
		
		// Entries are set depending on current segment selected
		TreeView segView = w.getL("mSegs").getView();		
		
		// Connect for getting selected row in tree view		
		rs3 = segView.getSelection();						
		rs3.connect(new Changed() {
			
			@Override
			public void onChanged(TreeSelection arg0) {	
			
				// Get selected string
				if( rs3.getSelected() != null ) {
					
					// Get selected cable
					selectedText = w.getL("mSegs").getModel().getValue(rs3.getSelected(), w.getL("mSegs").getHeader());
					
					// Get current segment based on selected segment 
					try {
						 //currentCell = ui.getSegmentByType(selectedText);
					} catch (Exception e) {
						
						e.printStackTrace();
					}									
				
					// Set everything else to current segment 						
					
				}							
			}
		});
	}
		
	private void modifyHandlers( final WidgetReferences w, final UIControllerNeuroML ui) {
		
		// Adding cells
		((Button)w.getW("mAddCell")).connect( new Clicked() {
			
			@Override
			public void onClicked(Button arg0) {
	
				
			}
		});
		
		// Removing cells
		((Button)w.getW("mRemCell")).connect( new Clicked() {
			
			@Override
			public void onClicked(Button arg0) {
		
				
			}
		});
		
		// Adding cables
		((Button)w.getW("mAddCable")).connect( new Clicked() {
			
			@Override
			public void onClicked(Button arg0) {
		
				
			}
		});
		
		// Removing cables
		((Button)w.getW("mRemCable")).connect( new Clicked() {
			
			@Override
			public void onClicked(Button arg0) {
		
				
			}
		});
		
		// Adding segments
		((Button)w.getW("mAddSeg")).connect( new Clicked() {
			
			@Override
			public void onClicked(Button arg0) {
		
				
			}
		});
		
		// Removing segments
		((Button)w.getW("mRemSeg")).connect( new Clicked() {
			
			@Override
			public void onClicked(Button arg0) {
		
				
			}
		});
		
		// Cell Name
		((Entry) w.getW("mCellName")).connect(new Activate() {
			
			@Override
			public void onActivate(Entry arg0) {
				
				
			}
		});
		
		// Cable Name
		((Entry) w.getW("mCableName")).connect(new Activate() {
			
			@Override
			public void onActivate(Entry arg0) {
				
				
			}
		});
		
		// Segment Name
		((Entry) w.getW("mSegName")).connect(new Activate() {
			
			@Override
			public void onActivate(Entry arg0) {
				
				
			}
		});
		
		// Proximal X
		((Entry) w.getW("mProX")).connect(new Activate() {
			
			@Override
			public void onActivate(Entry arg0) {
				
				
			}
		});
		
		// Proximal Y
		((Entry) w.getW("mProY")).connect(new Activate() {
			
			@Override
			public void onActivate(Entry arg0) {
				
				
			}
		});
		
		// Proximal Z
		((Entry) w.getW("mProZ")).connect(new Activate() {
			
			@Override
			public void onActivate(Entry arg0) {
				
				
			}
		});
		
		// Distal X
		((Entry) w.getW("mDisX")).connect(new Activate() {
			
			@Override
			public void onActivate(Entry arg0) {
				
				
			}
		});
		
		// Distal Y
		((Entry) w.getW("mDisY")).connect(new Activate() {
			
			@Override
			public void onActivate(Entry arg0) {
				
				
			}
		});
		
		// Distal Z
		((Entry) w.getW("mDisZ")).connect(new Activate() {
			
			@Override
			public void onActivate(Entry arg0) {
				
				
			}
		});	
	}
}
