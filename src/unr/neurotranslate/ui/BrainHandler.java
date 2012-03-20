package unr.neurotranslate.ui;
import java.util.ArrayList;

import org.gnome.glade.XML;
import org.gnome.gtk.Button;
import org.gnome.gtk.Entry;
import org.gnome.gtk.ListStore;
import org.gnome.gtk.TreeView;
import org.gnome.gtk.TreeViewColumn;
import org.gnome.gtk.Window;
import org.gnome.gtk.Button.Clicked;

public class BrainHandler {
	
	public XML glade;
	public XML glade2;
	public TreeView columnView;
	public TreeView stimulusView;
	public TreeView reportsView;
	public TreeView connectionView;
	public ArrayList<String> actBrainColumnList;
	public ArrayList<String> availBrainColumnList;
	public ArrayList<String> activeL;		// for debugging
	public ArrayList<String> availL;		// for debugging
	
	public BrainHandler( XML gladeObject, XML popupObject ) {
		
		glade = gladeObject;
		glade2 = popupObject;
		
		activeL = new ArrayList<String>();
		availL = new ArrayList<String>();

		setLists( ); 	
		
		setEntries( );

		modifyHandler( );
		
	}
	
	public void setLists() {
		
		// grab all widgets
		columnView = (TreeView) glade.getWidget( "brainColumnList" );		
		stimulusView = (TreeView) glade.getWidget( "brainStimulusList" );		
		reportsView = (TreeView) glade.getWidget( "brainReportsList" );		
		connectionView = (TreeView) glade.getWidget( "brainConnectionList" );		
		
		// Initialize all List Store models to build widget lists
		ListStore columnModel, stimulusModel, reportsModel, connectionModel;
		TreeViewColumn treeColumn = columnView.appendColumn();
		TreeViewColumn treeStimulus = stimulusView.appendColumn();
		TreeViewColumn treeReport = reportsView.appendColumn();
		TreeViewColumn treeConnection = connectionView.appendColumn();
		
		// TODO - Get brainColumnTypes ArrayList
		actBrainColumnList = new ArrayList<String>();
		actBrainColumnList.add("visual_cortex");
		actBrainColumnList.add("hypothalamus");
		actBrainColumnList.add("parietal_cortex");		
		ArrayList<String> brainStimulusList = new ArrayList<String>();
		brainStimulusList.add("visual");
		brainStimulusList.add("proprioceptive");
		//brainStimulusList.add("emily");
		ArrayList<String> brainReportsList = new ArrayList<String>();
		brainReportsList.add("hypothalamus_learning");
		brainReportsList.add("visual_voltage");
		brainReportsList.add("parietal_voltage");
		ArrayList<String> brainConnectionList = new ArrayList<String>();
		brainConnectionList.add("visual_hypothalamus");
		brainConnectionList.add("visual_parietal");	
		brainConnectionList.add("parietal_hypothalamus");	
		
		// Build models and set views
		columnModel = Utils.buildListModel( actBrainColumnList, treeColumn );		
		columnView.setModel( columnModel );
		stimulusModel = Utils.buildListModel( brainStimulusList, treeStimulus );		
		stimulusView.setModel( stimulusModel );
		reportsModel = Utils.buildListModel( brainReportsList, treeReport );		
		reportsView.setModel( reportsModel );
		connectionModel = Utils.buildListModel( brainConnectionList, treeConnection );		
		connectionView.setModel( connectionModel );	
	}
	
	public void setEntries () {
		
		// Grab all entry widgets
		Entry brainType = (Entry) glade.getWidget( "brainType" );
		Entry brainJob = (Entry) glade.getWidget( "brainJob" );
		Entry brainFSV = (Entry) glade.getWidget( "brainFSV" );
		Entry brainDuration = (Entry) glade.getWidget( "brainDuration" );
		Entry brainSeed = (Entry) glade.getWidget( "brainSeed" );
		
		// Set all entry widgets
		brainType.setText("brain_model_input");
		brainJob.setText("brain_model_output");
		brainFSV.setText("1000");
		brainDuration.setText("10");
		brainSeed.setText("-20");
	}
	
	public void modifyHandler() {
		
		// grab all widgets
		Button addColumn = (Button) glade.getWidget( "brainAddColumn" );
		Button addStimulus = (Button) glade.getWidget( "brainAddStimulus" );
		Button addReport = (Button) glade.getWidget( "brainAddReport" );
		Button addConnection = (Button) glade.getWidget( "brainAddConnection" );
		final Window popup = (Window) glade2.getWidget( "window2" );
		final Window main = (Window) glade.getWidget("window1");
		
		// set up connects 
		addColumn.connect( new Clicked() {
			
			@Override
			public void onClicked(Button arg0) {
						
				popup.show();
				ModifyPopup.updatePopup( "Column Types", actBrainColumnList, availBrainColumnList );

			}
		});	
		
		
	
	}
}
