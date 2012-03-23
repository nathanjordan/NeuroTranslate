package unr.neurotranslate.ui;
import java.util.ArrayList;

import org.gnome.glade.XML;
import org.gnome.gtk.Button;
import org.gnome.gtk.DataColumnString;
import org.gnome.gtk.Entry;
import org.gnome.gtk.ListStore;
import org.gnome.gtk.TreeView;
import org.gnome.gtk.TreeViewColumn;
import org.gnome.gtk.Window;
import org.gnome.gtk.Button.Clicked;

public class BrainHandler {
	
	public TreeView columnView;
	public TreeView stimulusView;
	public TreeView reportsView;
	public TreeView connectionView;
	public ArrayList<String> actBrainColumnList;
	public ArrayList<String> availBrainColumnList;
	public ArrayList<String> activeL;		// for debugging
	public ArrayList<String> availL;		// for debugging
	
	public BrainHandler() {
		
		activeL = new ArrayList<String>();
		availL = new ArrayList<String>();

		setLists( ); 	
		
		setEntries( );

		modifyHandler( );		
	}
	
	public void setLists() {
		
		// TODO - Get arraylists from data model
		actBrainColumnList = new ArrayList<String>();
		actBrainColumnList.add("visual_cortex");
		actBrainColumnList.add("hypothalamus");
		actBrainColumnList.add("parietal_cortex");		
		ArrayList<String> brainStimulusList = new ArrayList<String>();
		brainStimulusList.add("visual");
		brainStimulusList.add("proprioceptive");	
		ArrayList<String> brainReportsList = new ArrayList<String>();
		brainReportsList.add("hypothalamus_learning");
		brainReportsList.add("visual_voltage");
		brainReportsList.add("parietal_voltage");
		ArrayList<String> brainConnectionList = new ArrayList<String>();
		brainConnectionList.add("visual_hypothalamus");
		brainConnectionList.add("visual_parietal");	
		brainConnectionList.add("parietal_hypothalamus");	

		// Build models and set views
		ListEntity columnTypes = new ListEntity( actBrainColumnList, "bColTypes" );
		ListEntity stimulusInjects = new ListEntity( brainStimulusList, "bStimInjects" );
		ListEntity reports = new ListEntity( brainReportsList, "bReports" );
		ListEntity connections = new ListEntity( brainConnectionList, "bConnections" );		
	}
	
	public void setEntries () {
		
		// Grab all entry widgets
		Entry brainType = (Entry) GladeParseUtil.grabWidget( "brainType", "window1" );
		Entry brainJob = (Entry) GladeParseUtil.grabWidget( "brainJob", "window1" );
		Entry brainFSV = (Entry) GladeParseUtil.grabWidget( "brainFSV", "window1" );
		Entry brainDuration = (Entry) GladeParseUtil.grabWidget( "brainDuration", "window1" );
		Entry brainSeed = (Entry) GladeParseUtil.grabWidget( "brainSeed", "window1" );
		
		// Set all entry widgets
		brainType.setText("brain_model_input");
		brainJob.setText("brain_model_output");
		brainFSV.setText("1000");
		brainDuration.setText("10");
		brainSeed.setText("-20");
	}
	
	public void modifyHandler() {
		
		// Grab all button and window widgets
		Button modifyColumn = (Button)  GladeParseUtil.grabWidget( "bModColumn", "window1" );
		Button addStimulus = (Button)  GladeParseUtil.grabWidget( "brainAddStimulus", "window1" );
		Button addReport = (Button)  GladeParseUtil.grabWidget( "brainAddReport", "window1" );
		Button addConnection = (Button) GladeParseUtil.grabWidget( "brainAddConnection", "window1" );
		final Window popup = (Window) GladeParseUtil.grabWidget( "window2", "window2" );
		final Window main = (Window)GladeParseUtil.grabWidget( "window1", "window1" );
		
		// Set up connects for modifying a column
		modifyColumn.connect( new Clicked() {
			
			@Override
			public void onClicked(Button arg0) {
						
				popup.show();
				ModifyPopup.updatePopup( "Column Types", actBrainColumnList, availBrainColumnList );		
			}
		});	

	}
}
