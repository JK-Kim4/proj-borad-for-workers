import {
	ClassicEditor,
	AccessibilityHelp,
	Autoformat,
	AutoImage,
	AutoLink,
	Autosave,
	Bold,
	Code,
	CodeBlock,
	Essentials,
	GeneralHtmlSupport,
	Heading,
	HtmlEmbed,
	ImageBlock,
	ImageCaption,
	ImageInline,
	ImageInsert,
	ImageInsertViaUrl,
	ImageResize,
	ImageStyle,
	ImageTextAlternative,
	ImageToolbar,
	ImageUpload,
	Italic,
	Link,
	LinkImage,
	List,
	ListProperties,
	Markdown,
	MediaEmbed,
	Paragraph,
	PasteFromMarkdownExperimental,
	PasteFromOffice,
	SelectAll,
	ShowBlocks,
	SimpleUploadAdapter,
	Table,
	TableCaption,
	TableCellProperties,
	TableColumnResize,
	TableProperties,
	TableToolbar,
	TextPartLanguage,
	TextTransformation,
	Title,
	Undo
} from 'ckeditor5';

import translations from 'ckeditor5/translations/ko.js';

const editorConfig = {
	toolbar: {
		items: [
			'undo',
			'redo',
			'|',
			'showBlocks',
			'selectAll',
			'textPartLanguage',
			'|',
			'heading',
			'|',
			'bold',
			'italic',
			'code',
			'|',
			'link',
			'insertImage',
			'mediaEmbed',
			'insertTable',
			'codeBlock',
			'htmlEmbed',
			'|',
			'bulletedList',
			'numberedList',
			'|',
			'accessibilityHelp'
		],
		shouldNotGroupWhenFull: false
	},
	plugins: [
		AccessibilityHelp,
		Autoformat,
		AutoImage,
		AutoLink,
		Autosave,
		Bold,
		Code,
		CodeBlock,
		Essentials,
		GeneralHtmlSupport,
		Heading,
		HtmlEmbed,
		ImageBlock,
		ImageCaption,
		ImageInline,
		ImageInsert,
		ImageInsertViaUrl,
		ImageResize,
		ImageStyle,
		ImageTextAlternative,
		ImageToolbar,
		ImageUpload,
		Italic,
		Link,
		LinkImage,
		List,
		ListProperties,
		Markdown,
		MediaEmbed,
		Paragraph,
		PasteFromMarkdownExperimental,
		PasteFromOffice,
		SelectAll,
		ShowBlocks,
		SimpleUploadAdapter,
		Table,
		TableCaption,
		TableCellProperties,
		TableColumnResize,
		TableProperties,
		TableToolbar,
		TextPartLanguage,
		TextTransformation,
		Title,
		Undo
	],
	heading: {
		options: [
			{
				model: 'paragraph',
				title: 'Paragraph',
				class: 'ck-heading_paragraph'
			},
			{
				model: 'heading1',
				view: 'h1',
				title: 'Heading 1',
				class: 'ck-heading_heading1'
			},
			{
				model: 'heading2',
				view: 'h2',
				title: 'Heading 2',
				class: 'ck-heading_heading2'
			},
			{
				model: 'heading3',
				view: 'h3',
				title: 'Heading 3',
				class: 'ck-heading_heading3'
			},
			{
				model: 'heading4',
				view: 'h4',
				title: 'Heading 4',
				class: 'ck-heading_heading4'
			},
			{
				model: 'heading5',
				view: 'h5',
				title: 'Heading 5',
				class: 'ck-heading_heading5'
			},
			{
				model: 'heading6',
				view: 'h6',
				title: 'Heading 6',
				class: 'ck-heading_heading6'
			}
		]
	},
	htmlSupport: {
		allow: [
			{
				name: /^.*$/,
				styles: true,
				attributes: true,
				classes: true
			}
		]
	},
	image: {
		toolbar: [
			'toggleImageCaption',
			'imageTextAlternative',
			'|',
			'imageStyle:inline',
			'imageStyle:wrapText',
			'imageStyle:breakText',
			'|',
			'resizeImage'
		]
	},
	language: 'ko',
	link: {
		addTargetToExternalLinks: true,
		defaultProtocol: 'https://',
		decorators: {
			toggleDownloadable: {
				mode: 'manual',
				label: 'Downloadable',
				attributes: {
					download: 'file'
				}
			}
		}
	},
	list: {
		properties: {
			styles: true,
			startIndex: true,
			reversed: true
		}
	},
	table: {
		contentToolbar: ['tableColumn', 'tableRow', 'mergeTableCells', 'tableProperties', 'tableCellProperties']
	},
	translations: [translations]
};

ClassicEditor.create(document.querySelector('#editor'), editorConfig);
