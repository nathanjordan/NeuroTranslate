package unr.neurotranslate.ui.controller;

import java.util.ArrayList;
import unr.neurotranslate.model.Data;
import unr.neurotranslate.ncs.Column;
import unr.neurotranslate.ncs.ColumnShell;

public class UIControllerNCS {
	

	private Data _data;
	
	//////////////////////////////////////////////////////////////////////////////////////////
	////    Column Shells
	//////////////////////////////////////////////////////////////////////////////////////////
	
	public ArrayList<String> getColumnShells() {
		
		ArrayList<String> list = new ArrayList<String>();
		
		for( ColumnShell c : _data.ncs.columnShellList )
			
			list.add( c.type );
		
		return list;
		
		}
	
	public ColumnShell getColumnShellByType( String type ) throws Exception {
		
		for( ColumnShell c : _data.ncs.columnShellList )
			
			if( c.type == type )
				
				return c;
		
		throw new Exception("Column Shell Not found");
		
		}
	
	public ColumnShell addColumnShell() {
		
		ColumnShell c = new ColumnShell();
		
		c.type = "ColumnShell1";
		
		_data.ncs.columnShellList.add(c);
		
		return c;
		
		}
	
	public void removeColumnShell( String type ) throws Exception {
		
		for( ColumnShell c : _data.ncs.columnShellList )
			
			if( c.type == type ) {
				
				_data.ncs.columnShellList.remove(c);
				
				return;
				
				}
		
		throw new Exception("Column Shell Not found");
		
		}
	
	//////////////////////////////////////////////////////////////////////////////////////////
	////    Column Shells
	//////////////////////////////////////////////////////////////////////////////////////////
	
	public ArrayList<String> getColumns() {
		
		ArrayList<String> list = new ArrayList<String>();
		
		for( Column c : _data.ncs.columnList )
			
			list.add( c.type );
		
		return list;
		
		}
	
	public Column getColumnByType( String type ) throws Exception {
		
		for( Column c : _data.ncs.columnList )
			
			if( c.type == type )
				
				return c;
		
		throw new Exception("Column Not found");
		
		}
	
	public Column addColumn() {
		
		Column c = new Column();
		
		c.type = "ColumnShell1";
		
		_data.ncs.columnList.add(c);
		
		return c;
		
		}
	
	public void removeColumn( String type ) throws Exception {
		
		for( Column c : _data.ncs.columnList )
			
			if( c.type == type ) {
				
				_data.ncs.columnList.remove(c);
				
				return;
				
				}
		
		throw new Exception("Column Not found");
		
		}
>>>>>>> 5d63fce35162271745fc0983c2df41febce8efad
	
	}
