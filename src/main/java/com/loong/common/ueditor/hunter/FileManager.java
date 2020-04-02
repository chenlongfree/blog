package com.loong.common.ueditor.hunter;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.loong.blog.model.Attachment;
import com.loong.blog.service.IAttachmentService;
import com.loong.common.ueditor.PathFormat;
import com.loong.common.ueditor.define.AppInfo;
import com.loong.common.ueditor.define.BaseState;
import com.loong.common.ueditor.define.MultiState;
import com.loong.common.ueditor.define.State;
import com.loong.common.util.SpringUtil;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public class FileManager {

	private String dir = null;
	private String rootPath = null;
	private String[] allowFiles = null;
	private int count = 0;
	private String type = null;
	
	public FileManager ( Map<String, Object> conf ) {

		this.rootPath = (String)conf.get( "rootPath" );
		this.dir = this.rootPath + conf.get( "dir" );
		this.allowFiles = this.getAllowFiles( conf.get("allowFiles") );
		this.count = (Integer)conf.get( "count" );
		this.type = (String)conf.get( "type" );

	}
	
	public State listFile (int index ) {
		
		File dir = new File( this.dir );
		State state = null;

		if ( !dir.exists() ) {
			return new BaseState( false, AppInfo.NOT_EXIST );
		}
		
		if ( !dir.isDirectory() ) {
			return new BaseState( false, AppInfo.NOT_DIRECTORY );
		}

		/*Collection<File> list = FileUtils.listFiles( dir, this.allowFiles, true );
		
		if ( index < 0 || index > list.size() ) {
			state = new MultiState( true );
		} else {
			Object[] fileList = Arrays.copyOfRange( list.toArray(), index, index + this.count );
			state = this.getState( fileList );
		}*/
		IAttachmentService attachmentService = SpringUtil.getBean(IAttachmentService.class);
		Page<Attachment> page = attachmentService.page(new Page<>(index, this.count), new QueryWrapper<Attachment>().eq("type", this.type));
		if(page.getRecords().size() == 0) {
			state = new MultiState( true );
		} else {
			state = this.getState( page.getRecords() );
		}
		state.putInfo( "start", index );
		state.putInfo( "total", page.getTotal() );
		
		return state;
		
	}
	
	private State getState (List<Attachment> list ) {
		
		MultiState state = new MultiState( true );
		BaseState fileState = null;


		for ( Attachment a : list ) {
			fileState = new BaseState( true );
			fileState.putInfo( "url", a.getSrc() );
			fileState.putInfo( "title", a.getName() );
			state.addState( fileState );
		}
		
		return state;
		
	}
	
	private String getPath ( File file ) {
		
		String path = file.getAbsolutePath();
		
		return path.replace( this.rootPath, "/" );
		
	}
	
	private String[] getAllowFiles ( Object fileExt ) {
		
		String[] exts = null;
		String ext = null;
		
		if ( fileExt == null ) {
			return new String[ 0 ];
		}
		
		exts = (String[])fileExt;
		
		for ( int i = 0, len = exts.length; i < len; i++ ) {
			
			ext = exts[ i ];
			exts[ i ] = ext.replace( ".", "" );
			
		}
		
		return exts;
		
	}
	
}
