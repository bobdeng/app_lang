package com.palmwin.applang.bo.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.dom4j.Comment;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.QName;
import org.dom4j.dom.DOMAttribute;
import org.dom4j.dom.DOMComment;
import org.dom4j.dom.DOMDocument;
import org.dom4j.dom.DOMElement;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import com.palmwin.applang.bo.IApp;
import com.palmwin.applang.model.StringRes;

public class AndroidApp implements IApp {
	private String path;

	public AndroidApp(String path) {
		super();
		this.path = path;
	}

//	@Override
//	public Set<String> getKeys() {
//		File resFolder = new File(path, "res");
//		File[] valuesFolders = resFolder.listFiles(new FilenameFilter() {
//
//			@Override
//			public boolean accept(File dir, String name) {
//				return name.startsWith("values");
//			}
//		});
//		Set<String> keys = new HashSet<String>();
//		for (File valuesFolder : valuesFolders) {
//			SAXReader xmlReader = new SAXReader();
//			try {
//				Document doc = xmlReader.read(new File(valuesFolder,
//						"strings.xml"));
//				List<Element> elements = doc.getRootElement().elements();
//				for (Element e : elements) {
//					keys.add(e.attributeValue("name"));
//				}
//			} catch (DocumentException e) {
//			}
//		}
//		return keys;
//	}

	private File getXmlFile(String lang) {
		File resFolder = new File(path, "res");
		File valuesFolder = lang == null ? new File(resFolder, "values")
				: new File(resFolder, "values-" + lang);
		return new File(valuesFolder, "strings.xml");
	}

	@Override
	public Map<String, StringRes> getLang(String lang) {
		Map<String,StringRes> ret=getStrings(null,null);
		if(lang==null){
			return ret;
		}
		return getStrings(ret,lang);
		
	}
	private StringRes getRes(Map<String,StringRes> resource,String key){
		StringRes r =resource.get(key);
		if(r==null){
			return new StringRes();
		}else{
			return r;
		}
	}
	private Map<String,StringRes> getStrings(Map<String,StringRes> defaultResource,String lang){
		SAXReader xmlReader = new SAXReader();
		if(defaultResource==null)
		{
			defaultResource = new LinkedHashMap<String, StringRes>();
		}
		try {
			Document doc = xmlReader.read(getXmlFile(lang));
			for (int i = 0; i < doc.getRootElement().nodeCount(); i++) {
				Node node = doc.getRootElement().node(i);

				if (node.getNodeType() == Node.ELEMENT_NODE) {
					Element e = (Element) node;
					StringRes r = getRes(defaultResource,e.attributeValue("name"));
					r.setKey(e.attributeValue("name"));
					defaultResource.put(e.attributeValue("name"), r);
					if (e.getName().equals("string")) {
						r.setArray(false);
						if(lang==null)
						{
							r.setValue(e.getText());
						}else{
							r.setLang(e.getText());
						}
					} else {
						if (e.getName().equals("string-array")) {
							r.setArray(true);
							List<Element> items = e.elements();
							StringBuffer sbItems = new StringBuffer();
							if (items.size() > 0) {
								for (Element item : items) {
									sbItems.append(item.getText());
									sbItems.append("||");
								}
								sbItems.delete(sbItems.length() - 2,
										sbItems.length());
							} else {
								System.out.println("item is null :"
										+ r.getKey());
							}
							if(lang==null)
							{
								r.setValue(sbItems.toString());
							}else{
								r.setLang(sbItems.toString());
							}
						} else {
							System.out.println("other" + e);
						}

					}
				}
				//只有缺省的资源，需要保存注释
				if (lang==null && node.getNodeType() == Node.COMMENT_NODE) {
					Comment comment = (Comment) node;
					StringRes r = new StringRes();
					r.setValue(comment.getText());
					r.setComment(true);
					defaultResource.put(r.getValue(), r);
				}

			}
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
		}
		return defaultResource;
	}

	@Override
	public void writeLang(Map<String, StringRes> values, String lang)
			throws Exception {
		Document doc = new DOMDocument();
		Element root = new DOMElement("resources");
		doc.setRootElement(root);
		for (String key : values.keySet()) {
			StringRes res = values.get(key);
			if (res.isComment()) {
				root.addComment(res.getValue());

			} else {
				if (res.isArray()) {
					Element child = root.addElement("string-array");
					child.add(new DOMAttribute(new QName("name"), res.getKey()));
					String[] items = (lang==null?res.getValue():res.getLang()).split("\\|\\|");
					for (String item : items) {
						Element eItem = child.addElement("item");
						eItem.setText(item);
					}

				} else {
					Element child = root.addElement("string");
					child.add(new DOMAttribute(new QName("name"), res.getKey()));
					child.setText(lang==null?res.getValue():res.getLang());
				}
			}

		}
		OutputFormat outformat = OutputFormat.createPrettyPrint();
		outformat.setEncoding("utf-8");
		XMLWriter writer = new XMLWriter(new FileOutputStream(
				this.getXmlFile(lang)), outformat);
		writer.write(doc);
		writer.flush();

	}

}
