/**
 * @license Copyright (c) 2003-2015, CKSource - Frederico Knabben. All rights reserved.
 * For licensing, see LICENSE.md or http://ckeditor.com/license
 */

CKEDITOR.editorConfig = function( config ) {
	// Define changes to default configuration here. For example:
	// config.language = 'fr';
	// config.uiColor = '#AADC6E';
	config.allowedContent = true; //允許所有標籤
	//保留word的樣式
	config.pasteFromWordRemoveFontStyles = false; 
    config.pasteFromWordRemoveStyles = false;
    //字型設定
	config.font_names = '新細明體;標楷體;微軟正黑體;Arial;Arial Black;Comic Sans MS;Courier New;Tahoma;Times New Roman;Verdana;';
};