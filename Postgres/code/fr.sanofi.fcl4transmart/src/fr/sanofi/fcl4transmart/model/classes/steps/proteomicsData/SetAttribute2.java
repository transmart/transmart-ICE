/*******************************************************************************
 * Copyright (c) 2012 Sanofi-Aventis Recherche et Developpement.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Public License v3.0
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/gpl.html
 * 
 * Contributors:
 *    Sanofi-Aventis Recherche et Developpement - initial API and implementation
 ******************************************************************************/
package fr.sanofi.fcl4transmart.model.classes.steps.proteomicsData;

import java.io.File;

import fr.sanofi.fcl4transmart.controllers.FileHandler;
import fr.sanofi.fcl4transmart.model.classes.dataType.ProteomicsData;
import fr.sanofi.fcl4transmart.model.classes.workUI.proteomicsData.ProteomicsSetAttribute2UI;
import fr.sanofi.fcl4transmart.model.interfaces.DataTypeItf;
import fr.sanofi.fcl4transmart.model.interfaces.StepItf;
import fr.sanofi.fcl4transmart.model.interfaces.WorkItf;
/**
 *This class represents the step to set the second optional attribute for the sample to subject mapping file
 */	
public class SetAttribute2 implements StepItf{
	private WorkItf workUI;
	private DataTypeItf dataType;
	public SetAttribute2(DataTypeItf dataType){
		this.workUI=new ProteomicsSetAttribute2UI(dataType);
		this.dataType=dataType;
	}
	@Override
	public WorkItf getWorkUI() {
		return this.workUI;
	}
	public String toString(){
		return "Set attribute 2 (optional)";
	}
	public String getDescription(){
		return "This step allows defining a second optional attribute for samples.\n"+
				"The button 'Apply' allows setting all selected fields to the value in the field names 'Value'. All fields can be selected or deselected at the same time with buttons.\n"+
				"The button 'OK' allows updating the subject to sample mapping file.";
	}
	public boolean isAvailable(){
		try{
			if(((ProteomicsData)this.dataType).getRawFiles()==null || ((ProteomicsData)this.dataType).getRawFiles().size()==0) return false;
			if(((ProteomicsData)this.dataType).getColumnMappingFile()==null) return false;
			File stsmf=((ProteomicsData)this.dataType).getMappingFile();
			if(stsmf==null) return false;
			if(!FileHandler.checkPlatform(stsmf)) return false;
			return true;
		}
		catch(NullPointerException e){
			return false;
		}
	}
}
