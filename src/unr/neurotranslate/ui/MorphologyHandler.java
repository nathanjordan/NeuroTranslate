package unr.neurotranslate.ui;
import java.util.ArrayList;

import org.gnome.glade.XML;
import org.gnome.gtk.DataColumnString;
import org.gnome.gtk.Entry;
import org.gnome.gtk.ListStore;
import org.gnome.gtk.TreeIter;
import org.gnome.gtk.TreeView;
import org.gnome.gtk.TreeViewColumn;
import org.gnome.gtk.ComboBox.Changed;

public class MorphologyHandler {

	public XML glade;
	public XML glade2;
	public TreeView cellView;
	public TreeView segmentView;
	public TreeView cableView;
	
	public ArrayList<String> morphologyCellList; //temp 
	public ArrayList<String> morphologySegmentList;  //temp
	public ArrayList<String> morphologyCable1;
	public ArrayList<String> morphologyCable2;
	
	public MorphologyHandler( XML gladeObject, XML popupObject ) {
		
		glade = gladeObject;
		glade2 = popupObject;
		
		setLists( ); 	
		
		setEntries( );
		
	}

	
	public void setEntries() {

		Entry cellName = (Entry) glade.getWidget("entry1");
		Entry cableName = (Entry) glade.getWidget("entry91");
		Entry segmentName = (Entry) glade.getWidget( "morphSegmentName" );
		Entry proximalX = (Entry) glade.getWidget( "morphProximalX" );
		Entry proximalY = (Entry) glade.getWidget( "morphProximalY" );
		Entry proximalZ = (Entry) glade.getWidget( "morphProximalZ" );
		
		cellName.setText("pyramidal_cellA");
		cableName.setText("SomaCable");
		segmentName.setText( "soma" );
		proximalX.setText("10");
		proximalY.setText("0");
		proximalZ.setText("0");
	}

	public void setLists() {

		// grab all widgets
		cellView = (TreeView) glade.getWidget( "morphCellView" );
		segmentView = (TreeView) glade.getWidget( "morphSegmentView" );
		cableView = (TreeView) glade.getWidget("mophCableView");
		
		//segIDView = (ComboBox) glade.getWidget( "morphSegmentIDList" );
		//cableView = (ComboBox) glade.getWidget( "morphCableList" );
		
		// Initialize all List Store models to build widget lists
		ListStore cellModel, segmentModel, cableModel;
		TreeViewColumn treeCell = cellView.appendColumn();
		TreeViewColumn treeSegment = segmentView.appendColumn();
		TreeViewColumn treeSegment2 = segmentView.appendColumn();
		TreeViewColumn treeCable1 = cableView.appendColumn();
		TreeViewColumn treeCable2 = cableView.appendColumn();
		DataColumnString cellHeader = null;
		
		treeCell.setTitle("Name");
		
		treeCable1.setTitle("ID");
		treeCable2.setTitle("Name");
		
		treeSegment.setTitle("ID");
		treeSegment2.setTitle("Name");
		
		
		/*DataColumnString column1;		
		segIdModel = new ListStore(new DataColumn[] {
				column1 = new DataColumnString(),          
		        });
		 
	
		DataColumnString column2;		
		cableModel = new ListStore(new DataColumn[] {
				column2 = new DataColumnString(),          
		        });
		*/
		// Add temporary data to lists 
		morphologyCellList = new ArrayList<String>();
		morphologyCellList.add("pyramidal_cellA");
		morphologyCellList.add("pyramidal_cellB");
		morphologyCellList.add("pyramidal_cellC");
		
		
		morphologyCable1 = new ArrayList<String>();
		morphologyCable1.add("1");
		morphologyCable1.add("2");
		morphologyCable2 = new ArrayList<String>();
		morphologyCable2.add("SomaCable");
		morphologyCable2.add("DendriteCable");
		
		
		morphologySegmentList = new ArrayList<String>();
		morphologySegmentList.add("1");
		morphologySegmentList.add("2");
		morphologySegmentList.add("3");
	
		ArrayList<String> list2 = new ArrayList<String>();
		list2.add("Soma");
		list2.add("paper");
		list2.add("lizard");
		
		ArrayList<String> segId = new ArrayList<String>();
		segId.add("1");
		segId.add("2");
		segId.add("3");
		//segId.add("4");
		
		ArrayList<String> cable = new ArrayList<String>();
		cable.add("1");
		cable.add("1");
		
		TreeIter row1; 		
		TreeIter row2;    
		
		/*for( String s: segId ) {
			row1 = segIdModel.appendRow();		
			segIdModel.setValue( row1, column1, s );
				
		}
		
		for( String s: cable ) {
			row2 = cableModel.appendRow();
			cableModel.setValue( row2, column2, s );
		}*/

		// Build models and set views
		cellModel = Utils.buildListModel( morphologyCellList, treeCell, cellHeader );		
		cellView.setModel( cellModel );
		segmentModel = Utils.buildListModel2( morphologySegmentList, list2, treeSegment, treeSegment2 ); 
		segmentView.setModel( segmentModel );
		cableModel = Utils.buildListModel2( morphologyCable1, morphologyCable2, treeCable1, treeCable2 );
		cableView.setModel(cableModel);
		
		//segIdModel = Utils.buildModel( morphSegIDList, row1 ); //TODO - change util function to accept a TreeIter for comboboxes!
		
//		segIDView.setModel( segIdModel );
	//	CellRendererText renderer1 = new CellRendererText(segIDView);
		//renderer1.setText(column1);
		
		/*cableView.setModel( cableModel );
		CellRendererText renderer2 = new CellRendererText(cableView);
		renderer2.setText(column2);*/
	}
}
