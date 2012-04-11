package unr.neurotranslate.ui;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import org.gnome.gdk.EventExpose;
import org.gnome.gtk.Button;
import org.gnome.gtk.Entry;
import org.gnome.gtk.ScrolledWindow;
import org.gnome.gtk.TreeSelection;
import org.gnome.gtk.TreeView;
import org.gnome.gtk.Widget;
import org.gnome.gtk.Button.Clicked;
import org.gnome.gtk.TreeSelection.Changed;

import unr.neurotranslate.ncs.Cell;
import unr.neurotranslate.ncs.Compartment;
import unr.neurotranslate.ncs.SpikeShape;

public class CellHandler {
	// All array lists are for debugging
	public ArrayList<String> cCells;
	public ArrayList<String> cComps;
	public ArrayList<String> cSpikes;
	public TreeSelection rs1, rs2, rs3;
	public String selectedText;
	
	public ListEntity cells;
	public ListEntity compartments;
	public ListEntity spikeShapes;
	public ComboEntity comName;
	public ComboEntity spikeSel;
	public ComboEntity SFDSel;
	public ComboEntity learnSel;
	public ComboEntity stimSel;
	public ComboEntity colSel;
	public ComboEntity laySel;
	public ComboEntity cellSel;
	public ComboEntity compSel;
	
	public Entry cCellType;
	public Entry cComLab;
	public Entry cComX;
	public Entry cComY;
	public Entry cComType;
	public Entry cTMMean;
	public Entry cTMStd;
	public Entry cRMMean;
	public Entry cRMStd;
	public Entry cTMean;
	public Entry cTStd;
	public Entry cLRMean;
	public Entry cLRStd;
	public Entry cLCMean;
	public Entry cLCStd;
	public Entry cRPMean;
	public Entry cRPStd;
	public Entry cSpikeType;
	public Entry cSpikeVol;
	public Cell currentCell;
	public Compartment currentCompartment;
	public SpikeShape currentSpikes;
	
	public CellHandler() throws FileNotFoundException {
		
		ScrolledWindow c = (ScrolledWindow) GladeParseUtil.grabWidget("scrolledwindow4", "window1");
		
		c.connect(new Widget.ExposeEvent() {
			
			@Override
			public boolean onExposeEvent(Widget arg0, EventExpose arg1) {
				
				// fill out all entries/lists/combo boxes				
				
				
				return false;
			}
		});
		
		// Data sources
		cCells = new ArrayList<String>();
		cComps = new ArrayList<String>();
		cSpikes = new ArrayList<String>();
		selectedText = new String();
		
		// Entries
		cCellType = (Entry) GladeParseUtil.grabWidget("entry30", "window1");
		cComLab = (Entry) GladeParseUtil.grabWidget("entry28", "window1");
		cComX = (Entry) GladeParseUtil.grabWidget("entry29", "window1");
		cComY = (Entry) GladeParseUtil.grabWidget("entry32", "window1");
		cComType = (Entry) GladeParseUtil.grabWidget("entry31", "window1");
		cTMMean = (Entry) GladeParseUtil.grabWidget("entry64", "window1");
		cTMStd = (Entry) GladeParseUtil.grabWidget("entry65", "window1");
		cRMMean = (Entry) GladeParseUtil.grabWidget("entry62", "window1");
		cRMStd = (Entry) GladeParseUtil.grabWidget("entry63", "window1");
		cTMean = (Entry) GladeParseUtil.grabWidget("entry60", "window1");
		cTStd = (Entry) GladeParseUtil.grabWidget("entry61", "window1");
		cLRMean = (Entry) GladeParseUtil.grabWidget("entry58", "window1");
		cLRStd = (Entry) GladeParseUtil.grabWidget("entry59", "window1");
		cLCMean = (Entry) GladeParseUtil.grabWidget("entry56", "window1");
		cLCStd = (Entry) GladeParseUtil.grabWidget("entry57", "window1");
		cRPMean = (Entry) GladeParseUtil.grabWidget("entry48", "window1");
		cRPStd = (Entry) GladeParseUtil.grabWidget("entry49", "window1");
		cSpikeType = (Entry) GladeParseUtil.grabWidget("entry40", "window1");
		cSpikeVol = (Entry) GladeParseUtil.grabWidget("entry33", "window1");
		
		// Lists
		cells = new ListEntity( "cCells", "window1" );
		compartments = new ListEntity( "Compartments", "window1" );
		spikeShapes = new ListEntity( "SpikeShapes", "window1" );
		comName = new ComboEntity( "combobox10", "window1" );
		spikeSel = new ComboEntity( "combobox3", "window1" );
		SFDSel = new ComboEntity( "combobox11", "window1" );
		learnSel = new ComboEntity( "combobox12", "window1" );
		stimSel = new ComboEntity( "combobox4", "window1" );
		colSel = new ComboEntity( "combobox5", "window1" );
		laySel = new ComboEntity( "combobox6", "window1" );
		cellSel = new ComboEntity( "combobox7", "window1" );
		compSel = new ComboEntity( "combobox8", "window1" );
		
		setLists();
		
		setEntries();
		
		modifyHandlers();
		
	}
	
	public void setLists() throws FileNotFoundException {
	
	
				
	}
	
	public void setEntries() {
		
		// Entries are set depending on current cell selected
		TreeView cellView = cells.getView();		
		
		// Connect for getting selected row in tree view		
		rs1 = cellView.getSelection();
		rs1.connect(new Changed() {
			
			@Override
			public void onChanged(TreeSelection arg0) {	
			
				// Get selected string
				if( rs1.getSelected() != null ) {
					
					// Get selected cell
					selectedText = cells.getModel().getValue(rs1.getSelected(), cells.getHeader());

					// Get current cell based on cell
					// currentCell = getCellByType(selectedText);									
				
					// Set everything else to current
					//cCellType.setText(currentCell.type);
					// TODO - set compartment name and all the entries
					
					
				}							
			}
		});
				
		// Entries are set depending on current compartments selected
		TreeView compView = compartments.getView();		
		
		// Connect for getting selected row in tree view		
		rs2 = compView.getSelection();
		rs2.connect(new Changed() {
			
			@Override
			public void onChanged(TreeSelection arg0) {	
			
				// Get selected string
				if( rs2.getSelected() != null ) {
					
					// Get selected compartments
					selectedText = compartments.getModel().getValue(rs2.getSelected(), compartments.getHeader());

					// Get current compartments based on compartments
					// currentCompartment = getCompartmentByType(selectedText);									
				
					// Set everything else to current 
					// TODO - Set spike shape
					/*cComType.setText(currentCompartment.type);
					cTMMean.setText(currentCompartment.tauMembrane.mean.toString());
					cTMStd.setText(currentCompartment.tauMembrane.stdev.toString());
					cRMMean.setText(currentCompartment.rMembrane.mean.toString());
					cRMStd.setText(currentCompartment.rMembrane.stdev.toString());
					cTMean.setText(currentCompartment.threshold.mean.toString());
					cTStd.setText(currentCompartment.threshold.stdev.toString());
					cLRMean.setText(currentCompartment.leakReversal.mean.toString());
					cLRStd.setText(currentCompartment.leakReversal.stdev.toString());
					cLCMean.setText(currentCompartment.leakConductance.mean.toString());
					cLCStd.setText(currentCompartment.leakConductance.stdev.toString());
					cRPMean.setText(currentCompartment.vmRest.mean.toString());
					cRPStd.setText(currentCompartment.vmRest.stdev.toString());*/
				}							
			}
		});
		
		// Entries are set depending on spike selected
		TreeView spikeView = spikeShapes.getView();		
		
		// Connect for getting selected row in tree view		
		rs3 = spikeView.getSelection();
		rs3.connect(new Changed() {
			
			@Override
			public void onChanged(TreeSelection arg0) {	
			
				// Get selected string
				if( rs3.getSelected() != null ) {
					
					// Get selected spike
					selectedText = spikeShapes.getModel().getValue(rs3.getSelected(), spikeShapes.getHeader());

					// Get current spike shape based on selected spike
					// currentSpikes = getSpikeByType(selectedText);									
				
					// Set everything else to current
					/*cSpikeType.setText(currentSpikes.type);
					cSpikeVol.setText(currentSpikes.voltages.toString());*/
	
				}							
			}
		});
	}
	
	public void modifyHandlers() throws FileNotFoundException {
	
		//Buttons for add/removing
		Button addCell = (Button) GladeParseUtil.grabWidget( "cCellAdd", "window1" );
		Button remCell = (Button) GladeParseUtil.grabWidget( "cCellRem", "window1" );
		Button addComp = (Button) GladeParseUtil.grabWidget( "cCompAdd", "window1" );
		Button remComp = (Button) GladeParseUtil.grabWidget( "cCompRem", "window1" );
		Button addSpike = (Button) GladeParseUtil.grabWidget( "cSpikeAdd", "window1" );
		Button remSpike = (Button) GladeParseUtil.grabWidget( "cSpikeRem", "window1" );
		
		// Connect for adding a cell
		addCell.connect( new Clicked() {
			
			@Override
			public void onClicked(Button arg0) {
				
				cells.addData( "new cell");
				
			}
		});		
		
		// Connect for removing a cell
		remCell.connect( new Clicked() {
			
			@Override
			public void onClicked(Button arg0) {
			
				cells.removeData( );
				
			}
		});	
		
		// Connect for adding a compartment
		addComp.connect( new Clicked() {
			
			@Override
			public void onClicked(Button arg0) {
				
				compartments.addData( "new compartment" );
			}
		});
		
		// Connect for removing a compartment
		remComp.connect(new Clicked() {
			
			@Override
			public void onClicked(Button arg0) {
				
				compartments.removeData();
				
			}
		});
		
		// Connect for adding a Spike Shape
		addSpike.connect( new Clicked() {
			
			@Override
			public void onClicked(Button arg0) {
				
				spikeShapes.addData( "new spike shape" );
				
			}
		});
		
		// Connect for removing a Spike Shape
		remSpike.connect( new Clicked() {
			
			@Override
			public void onClicked(Button arg0) {
				
				spikeShapes.removeData();
				
			}
		});
	}
}
