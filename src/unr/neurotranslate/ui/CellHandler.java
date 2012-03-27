package unr.neurotranslate.ui;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import org.gnome.gtk.Button;
import org.gnome.gtk.Entry;
import org.gnome.gtk.Button.Clicked;

public class CellHandler {
	// All array lists are for debugging
	public ArrayList<String> cCells;
	public ArrayList<String> cComps;
	public ArrayList<String> cSpikes;
	
	public ListEntity cells;
	public ListEntity compartments;
	public ListEntity spikeShapes;
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
	
	
	public CellHandler() throws FileNotFoundException {
		
		// Data sources
		cCells = new ArrayList<String>();
		cComps = new ArrayList<String>();
		cSpikes = new ArrayList<String>();
		
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
		cells = new ListEntity( cCells, "cCells", "window1" );
		compartments = new ListEntity( cComps, "Compartments", "window1" );
		spikeShapes = new ListEntity( cSpikes, "SpikeShapes", "window1" );
		
		
		setLists();
		
		setEntries();
		
		modifyHandlers();
		
	}
	
	public void setLists() throws FileNotFoundException {
	
	
				
	}
	
	public void setEntries() {
		
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
