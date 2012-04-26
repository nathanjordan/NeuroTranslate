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
import org.morphml.metadata.schema.Point;
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
	
	
	public MorphologyHandler( final WidgetReferences w, final UIControllerNeuroML ui ) {

		w.getW("morphScroll").connect(new Widget.ExposeEvent() {
			
			@Override
			public boolean onExposeEvent(Widget arg0, EventExpose arg1) {
			
				//Refresh lists
				w.getL("mCells").listToModel( ui.getCells() );		
				
				return false;
			}
		});	
		
		setEntries( w, ui );
		
		modifyHandlers( w, ui );
		
	}

	public void setEntries(final WidgetReferences w, final UIControllerNeuroML ui) {
		
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
						 currentCell = ui.getCellByName(selectedText);
					} catch (Exception e) {
						
						e.printStackTrace();
					}									
				
					// Set everything else to current cell 						
					((Entry) w.getW("mCellName")).setText(currentCell.getName());
					w.getL("mCables").listToModel( ui.getCables( currentCell ) );
					w.getL("mSegs").listToModel( ui.getSegments( currentCell ) );					
					
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
					
					// Get current cable based on selected cable 
					try {
						 currentCable = ui.getCableByName( currentCell, selectedText);
					} catch (Exception e) {
						
						e.printStackTrace();
					}									
				
					// Set everything else to current cable 						
					((Entry) w.getW("mCableName")).setText( currentCable.getName() );
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
						 currentSegment = ui.getSegmentByName( currentCell, selectedText);
					} catch (Exception e) {
						
						e.printStackTrace();
					}									
				
					// Set everything else to current segment 						
					((Entry) w.getW("mSegName")).setText( currentSegment.getName() );					
					((Entry) w.getW("mProX")).setText( Double.toString(currentSegment.getProximal().getX()) );
					((Entry) w.getW("mProY")).setText( Double.toString(currentSegment.getProximal().getY()) );
					((Entry) w.getW("mProZ")).setText( Double.toString(currentSegment.getProximal().getZ()) );
					((Entry) w.getW("mDisX")).setText( Double.toString(currentSegment.getDistal().getX()) );
					((Entry) w.getW("mDisY")).setText( Double.toString(currentSegment.getDistal().getY()) );
					((Entry) w.getW("mDisZ")).setText( Double.toString(currentSegment.getDistal().getZ()) );
				}							
			}
		});
	}
		
	private void modifyHandlers( final WidgetReferences w, final UIControllerNeuroML ui) {
		
		// Adding cells
		((Button)w.getW("mAddCell")).connect( new Clicked() {
			
			@Override
			public void onClicked(Button arg0) {
	
				currentCell = ui.addCell();
				w.getL("mCells").addData( currentCell.getName() );
				w.getC("nCellSel").setChanged(true);	
				
			}
		});
		
		// Removing cells
		((Button)w.getW("mRemCell")).connect( new Clicked() {
			
			@Override
			public void onClicked(Button arg0) {
		
				try {
					ui.removeCell( w.getL("mCells").getSelected() );
				} catch (Exception e) {					
					//e.printStackTrace();
				}
				
				w.getL("mCells").removeData();
				((Entry) w.getW("mCellName")).setText("");
				w.getC("nCellSel").setChanged(true);	
				
			}
		});
		
		// Adding cables
		((Button)w.getW("mAddCable")).connect( new Clicked() {
			
			@Override
			public void onClicked(Button arg0) {
		
				//currentCable = ui.addCable();
				//w.getL("mCables").addData( currentCable.getName() );
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
		
				currentSegment = ui.addSegment(currentCell);
				w.getL("mSegs").addData( currentSegment.getName() );
				
				Point p = new Point();
				p.setX(0);
				p.setY(0);
				p.setZ(0);
				currentSegment.setProximal(p);
				currentSegment.setDistal(p);				
			}
		});
		
		// Removing segments
		((Button)w.getW("mRemSeg")).connect( new Clicked() {
			
			@Override
			public void onClicked(Button arg0) {
		
				try {
					ui.removeSegment( currentCell, w.getL("mSegs").getSelected() );
				} catch (Exception e) {					
					//e.printStackTrace();
				}
				
				w.getL("mSegs").removeData();
				((Entry) w.getW("mSegName")).setText("");
				((Entry) w.getW("mProX")).setText("");
				((Entry) w.getW("mProY")).setText("");
				((Entry) w.getW("mProZ")).setText("");
				((Entry) w.getW("mDisX")).setText("");
				((Entry) w.getW("mDisY")).setText("");
				((Entry) w.getW("mDisZ")).setText("");
			}
			
		});
		
		// Cell Name
		((Entry) w.getW("mCellName")).connect(new Activate() {
			
			@Override
			public void onActivate(Entry arg0) {
				
				w.getL("mCells").removeData();		
				w.getL("mCells").addData(arg0.getText());	
				w.getL("mCells").getView().grabFocus();
				currentCell.setName(arg0.getText());	
			}
		});
		
		// Cable Name
		((Entry) w.getW("mCableName")).connect(new Activate() {
			
			@Override
			public void onActivate(Entry arg0) {
				
				w.getL("mCables").removeData();		
				w.getL("mCables").addData(arg0.getText());	
				w.getL("mCables").getView().grabFocus();
				currentCable.setName(arg0.getText());
				
			}
		});
		
		// Segment Name
		((Entry) w.getW("mSegName")).connect(new Activate() {
			
			@Override
			public void onActivate(Entry arg0) {
				
				w.getL("mSegs").removeData();		
				w.getL("mSegs").addData(arg0.getText());	
				w.getL("mSegs").getView().grabFocus();
				currentSegment.setName(arg0.getText());
			}
		});
		
		// Proximal X
		((Entry) w.getW("mProX")).connect(new Activate() {
			
			@Override
			public void onActivate(Entry arg0) {
			
				try {
					double d = Double.parseDouble(arg0.getText());					
					Point point = new Point();
					point.setX(d);
					point.setY( Double.parseDouble(((Entry) w.getW("mProY")).getText()) );
					point.setZ( Double.parseDouble(((Entry) w.getW("mProZ")).getText()) );					
					currentSegment.setProximal(point);
				} catch( NumberFormatException nfe ) {
					arg0.setText("");
				}
			}
		});
		
		// Proximal Y
		((Entry) w.getW("mProY")).connect(new Activate() {
			
			@Override
			public void onActivate(Entry arg0) {
				
				try {
					double d = Double.parseDouble(arg0.getText());					
					Point point = new Point();
					point.setX( Double.parseDouble(((Entry) w.getW("mProX")).getText()) );
					point.setY(d);					
					point.setZ( Double.parseDouble(((Entry) w.getW("mProZ")).getText()) );					
					currentSegment.setProximal(point);
				} catch( NumberFormatException nfe ) {
					arg0.setText("");
				}
			}	
						
		});
		
		// Proximal Z
		((Entry) w.getW("mProZ")).connect(new Activate() {
			
			@Override
			public void onActivate(Entry arg0) {
				
				try {
					double d = Double.parseDouble(arg0.getText());					
					Point point = new Point();
					point.setX( Double.parseDouble(((Entry) w.getW("mProX")).getText()) );
					point.setY( Double.parseDouble(((Entry) w.getW("mProY")).getText()) );					
					point.setZ( d );					
					currentSegment.setProximal(point);
				} catch( NumberFormatException nfe ) {
					arg0.setText("");
				}
			}
		});
		
		// Distal X
		((Entry) w.getW("mDisX")).connect(new Activate() {
			
			@Override
			public void onActivate(Entry arg0) {
				
				try {
					double d = Double.parseDouble(arg0.getText());					
					Point point = new Point();
					point.setX(d);
					point.setY( Double.parseDouble(((Entry) w.getW("mDisX")).getText()) );
					point.setZ( Double.parseDouble(((Entry) w.getW("mDisY")).getText()) );					
					currentSegment.setDistal(point);
				} catch( NumberFormatException nfe ) {
					arg0.setText("");
				}				
			}
		});
		
		// Distal Y
		((Entry) w.getW("mDisY")).connect(new Activate() {
			
			@Override
			public void onActivate(Entry arg0) {
				
				try {
					double d = Double.parseDouble(arg0.getText());					
					Point point = new Point();
					point.setX( Double.parseDouble(((Entry) w.getW("mDisX")).getText()) );
					point.setY(d);					
					point.setZ( Double.parseDouble(((Entry) w.getW("mDisZ")).getText()) );					
					currentSegment.setDistal(point);
				} catch( NumberFormatException nfe ) {
					arg0.setText("");
				}				
			}
		});
		
		// Distal Z
		((Entry) w.getW("mDisZ")).connect(new Activate() {
			
			@Override
			public void onActivate(Entry arg0) {
				
				try {
					double d = Double.parseDouble(arg0.getText());					
					Point point = new Point();
					point.setX( Double.parseDouble(((Entry) w.getW("mDisX")).getText()) );
					point.setY( Double.parseDouble(((Entry) w.getW("mDisY")).getText()) );					
					point.setZ( d );					
					currentSegment.setDistal(point);
				} catch( NumberFormatException nfe ) {
					arg0.setText("");
				}				
			}
		});	
	}
}
