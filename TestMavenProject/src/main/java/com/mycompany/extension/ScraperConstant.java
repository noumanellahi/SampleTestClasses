package com.mycompany.extension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public final class ScraperConstant {
	public static final String UTF8 = "UTF-8";
	public static final String BLANK = " ";
	public final static String SCRAPER = "scraper";
	public final static String SCRAPE_RAW = "scraperRaw";
	public final static String SCRAPE_HWP_FILE_NAME = "hwpFileName";
	public static final String COMMA = ",";

	public static final String SLASH = "\\";
	public static final String INDEX_HTML = "\\index.html";
	public static final String CSS_BASE_URL = "\\styles.css";
	public static final String PDF_FILE = "\\%s%s";

	public final static String FILENAME = "example";
	public final static String FilterData = "FilterData";
	public final static String IsSkipEmptyPages = "IsSkipEmptyPages";
	public final static String SelectPdfVersion = "SelectPdfVersion";
	public final static String ExportBookmarks = "ExportBookmarks";
	public final static String ExportNotes = "ExportNotes";

	public final static String FILE = "file";

	/**
	 * File extensions.
	 */
	public static final String PDF_EXTENSION = ".pdf";
	public static final String DOC_EXTENSION = ".doc";
	public static final String DOCX_EXTENSION = ".docx";
	public static final String ODT_EXTENSION = ".odt";
	public static final String RTF_EXTENSION = ".rtf";
	public static final String TXT_EXTENSION = ".txt";
	public static final String MHT_EXTENSION = ".mht";
	public static final String PPT_EXTENSION = ".ppt";
	public static final String PPTX_EXTENSION = ".pptx";
	public static final String XLS_EXTENSION = ".xls";
	public static final String XLSX_EXTENSION = ".xlsx";
	public static final String DOT_EXTENSION = ".dot";
	public static final String DOTX_EXTENSION = ".dotx";
	public static final String HWP_EXTENSION = ".hwp";
	public static final String ZIP_EXTENSION = ".zip";
	public static final String ZIP_PLUS_EXTENSION = ".zip";
	public static final String HTM_EXTENSION = ".htm";
	public static final String HTML_EXTENSION = ".html";
	public static final String PNG_EXTENSION = ".png";
	public static final String JPG_EXTENSION = ".jpg";
	public static final String JPEG_EXTENSION = ".jpeg";
	public static final String XML_EXTENSION = ".xml";

	// New additional file types added on 26Oct21
	public static final String CSV_EXTENSION = ".csv";
	public static final String JSON_EXTENSION = ".json";
	public static final String LOG_EXTENSION = ".log";
	public static final String BMP_EXTENSION = ".bmp";
	public static final String GIF_EXTENSION = ".gif";
	public static final String SVG_EXTENSION = ".svg";
	public static final String TIF_EXTENSION = ".tif";
	public static final String TIFF_EXTENSION = ".tiff";
	public static final String TAR_EXTENSION = ".tar";
	public static final String TAR_GZ_EXTENSION = ".tar.gz";
	public static final String RAR_EXTENSION = ".rar";
	public static final String TMP_EXTENSION = ".tmp";

	/**
	 * File types.
	 */
	public static final String PDF = "pdf";
	public static final String DOC = "doc";
	public static final String DOCX = "docx";
	public static final String ODT = "odt";
	public static final String RTF = "rtf";
	public static final String TXT = "txt";
	public static final String MHT = "mht";
	public static final String PPT = "ppt";
	public static final String PPTX = "pptx";
	public static final String XLS = "xls";
	public static final String XLSX = "xlsx";
	public static final String DOT = "dot";
	public static final String DOTX = "dotx";
	public static final String HWP = "hwp";
	public static final String ZIP = "zip";
	public static final String ZIP_PLUS = "zip+";
	public static final String HTM = "htm";
	public static final String HTML = "html";
	public static final String IMG = "img";
	public static final String PNG = "png";
	public static final String JPG = "jpg";
	public static final String JPEG = "jpeg";
	public static final String XML = "xml";
	public static final String ALL = "all";
	public static final String MULTISPLIT = "multisplit";
	public static final String SINGLEPAGE = "single";
	public static final String TMP = "tmp";

	// New additional file types added on 26Oct21
	public static final String CSV = "csv";
	public static final String JSON = "json";
	public static final String LOG = "log";
	public static final String BMP = "bmp";
	public static final String GIF = "gif";
	public static final String SVG = "svg";
	public static final String TIF = "tif";
	public static final String TIFF = "tiff";
	public static final String TAR = "tar";
	public static final String TAR_GZ = "tar.gz";
	public static final String RAR = "rar";

	/**
	 * Map with extensions as key and file type as value.
	 */
	public static final Map<String, String> extensionFileType = new HashMap<String, String>();

	/**
	 * Map with HTTP status codes adn related messages.
	 */
	public static final Map<Integer, String> httpStatusCodes = new HashMap<Integer, String>();

	/**
	 * Chrome arguments
	 */
	public static final List<String> chromeOptions = new ArrayList<>();

	/**
	 * Chrome preferences
	 */
	public static final HashMap<String, Object> chromePrefs = new HashMap<String, Object>();

	/**
	 * Chrome arguments
	 */
	public static final List<String> customUserAgentSources = new ArrayList<>();

	/**
	 * Initializing maps and lists
	 */
	static {
		extensionFileType.put(PDF_EXTENSION, PDF);
		extensionFileType.put(DOC_EXTENSION, DOC);
		extensionFileType.put(DOCX_EXTENSION, DOC);
		extensionFileType.put(ODT_EXTENSION, ODT);
		extensionFileType.put(RTF_EXTENSION, RTF);
		extensionFileType.put(TXT_EXTENSION, TXT);
		extensionFileType.put(MHT_EXTENSION, MHT);
		extensionFileType.put(PPT_EXTENSION, PPT);
		extensionFileType.put(PPTX_EXTENSION, PPT);
		extensionFileType.put(XLS_EXTENSION, XLS);
		extensionFileType.put(XLSX_EXTENSION, XLS);
		extensionFileType.put(DOT_EXTENSION, DOT);
		extensionFileType.put(DOTX_EXTENSION, DOT);
		extensionFileType.put(HWP_EXTENSION, HWP);
		extensionFileType.put(ZIP_EXTENSION, ZIP);
		extensionFileType.put(HTM_EXTENSION, HTML);
		extensionFileType.put(HTML_EXTENSION, HTML);
		// image extension type
		extensionFileType.put(PNG_EXTENSION, IMG);
		extensionFileType.put(JPG_EXTENSION, IMG);
		extensionFileType.put(JPEG_EXTENSION, IMG);
		extensionFileType.put(BMP_EXTENSION, IMG);
		extensionFileType.put(GIF_EXTENSION, IMG);
		extensionFileType.put(SVG_EXTENSION, IMG);
		extensionFileType.put(TIF_EXTENSION, IMG);
		extensionFileType.put(TIFF_EXTENSION, IMG);
		// new additional file types added on 26Oct21
		extensionFileType.put(XML_EXTENSION, XML);
		extensionFileType.put(CSV_EXTENSION, CSV);
		extensionFileType.put(JSON_EXTENSION, JSON);
		extensionFileType.put(LOG_EXTENSION, LOG);

		extensionFileType.put(TAR_EXTENSION, TAR);
		extensionFileType.put(TAR_GZ_EXTENSION, TAR_GZ);
		extensionFileType.put(RAR_EXTENSION, RAR);

		chromeOptions.add("--no-sandbox"); // Bypass OS security model
		chromeOptions.add("--ignore-certificate-errors");
		chromeOptions.add("--start-maximized");
		chromeOptions.add("--window-size=1920,1080");
		chromeOptions.add("--disable-dev-shm-usage");
		chromeOptions.add("--disable-gpu");
		chromeOptions.add("--incognito");
		chromeOptions.add("--headless");

		chromePrefs.put("profile.default_content_settings.popups", 0);
		chromePrefs.put("safebrowsing.enabled", false);

		httpStatusCodes.put(300, "Multiple Choices");
		httpStatusCodes.put(301, "Moved Permanently");
		httpStatusCodes.put(302, "Payment required");
		httpStatusCodes.put(307, "Temporary Redirect");
		httpStatusCodes.put(308, "Permanent Redirect");
		httpStatusCodes.put(400, "Bad request");
		httpStatusCodes.put(401, "Authorization required");
		httpStatusCodes.put(402, "Payment required");
		httpStatusCodes.put(403, "Access forbidden");
		httpStatusCodes.put(404, "Page not found");
		httpStatusCodes.put(408, "Request Timeout");
		httpStatusCodes.put(429, "Too Many Requests");
		httpStatusCodes.put(500, "Internal Server Error");
		httpStatusCodes.put(501, "Not Implemented");
		httpStatusCodes.put(502, "Bad Gateway");
		httpStatusCodes.put(503, "Service Unavailable");
		httpStatusCodes.put(504, "Gateway Timeout");
		httpStatusCodes.put(502, "Network Authentication Required");
		httpStatusCodes.put(908, "This URL can’t be reached");

		customUserAgentSources.add("link.springer.com");
		customUserAgentSources.add("www.springer.com");
		customUserAgentSources.add("www.nature.com");
		customUserAgentSources.add("www.springeropen.com");
		customUserAgentSources.add("connect.springerpub.com");
		customUserAgentSources.add("www.springerpub.com");
		customUserAgentSources.add("www.scientificamerican.com");
		customUserAgentSources.add("www.biomedcentral.com");
		customUserAgentSources.add("www.ingentaconnect.com");
		customUserAgentSources.add("www.palgrave.com");
		customUserAgentSources.add("www.atlantis-press.com");
		customUserAgentSources.add("www.springeronline.com");
		customUserAgentSources.add("www.springerprofessional.de");
		customUserAgentSources.add("www.springermedizin.de");
		customUserAgentSources.add("bunsen.springernature.app");

	}

	public static final String CONTENT_TYPE = "Content-Type";

	public static final String DATA_OUTPUT_TYPE_FILE = "file";
	public static final String DATA_OUTPUT_TYPE_XML = "xml";
	public static final String DATA_OUTPUT_TYPE_SCREENSHOT = "screenshot";

//	public static final String PNG_EXTENSION = ".png";

	public static final List<String> headerTypeForPPT = new ArrayList<String>(
			Arrays.asList("application/vnd.ms-powerpoint",
					"application/vnd.openxmlformats-officedocument.presentationml.presentation"));

	public static final List<String> headerTypeForTXT = new ArrayList<String>(
			Arrays.asList("text/plain", "application/msword", "application/vnd.oasis.opendocument.text"));

	public static final List<String> headerTypeForHtml = new ArrayList<String>(
			Arrays.asList("Content-Type", "Content-type", "content-type"));

	public static final List<String> contentDispositionHeaderName = new ArrayList<String>(
			Arrays.asList("Content-Disposition", "Content-disposition", "content-disposition"));

	public static final List<String> headerValuesForPdf = new ArrayList<String>(
			Arrays.asList("application/pdf", "application/octet-stream", "application/octet-stream;charset=UTF-8",
					"application/octet-stream;charset=utf-8", "application/pdf;charset=UTF-8"));

	// docx, doc|dot => msword
	public static final List<String> headerValuesForDoc = new ArrayList<String>(Arrays.asList(
			"application/vnd.openxmlformats-officedocument.wordprocessingml.document", "application/octet-stream",
			"application/octet-stream;charset=UTF-8", "application/rtf;charset=UTF-8"));

	public static final List<String> headerValuesForHtml = new ArrayList<String>(
			Arrays.asList("text/html; charset=UTF-8", "text/html;charset=UTF-8", "text/html; charset=utf-8",
					"text/html;charset=utf-8", "text/html", "text/html; charset=ISO-8859-1",
					"text/html; charset=iso-8859-1", "text/html; charset=GBK"));

	// XLS, XLSX
	public static final List<String> headerValuesForXLS = new ArrayList<String>(Arrays
			.asList("application/vnd.ms-excel", "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"));

	public static final String EFS = "efs";
	public static final String S3 = "s3";

	public static final int SCHEDULER_CRON_TIME_IN_MINUTES = 2;

	public static final String htmlToPdfBasePageDescription = "Source HTML Page";

	public static final String IMAGE_FILE_Description = "Image File";

	public static final String COMPANY_INFORMATION = "Company Information";

	public static final String SPECIAL_CHARACTER_REGEX = "[\\[\\]\\.{}()!*+~`%^<>$&+,:;=?@#|'\"\\\\/\\s\u2003]";
	public static final String INVALID_CHARACTER_REGEX = "[\u0000-\u0008\u000E-\u001F\u007F-\u0084\u0086-\u009F\uD800-\uDFFF\uFDD0-\uFDEF\u000B\u000C\uFFFE\uFFFF]";

	public static final String ORIGINAL_FILENAME_POSTFIX = "";

	public static final String SCREENSHOT = "_SCREENSHOT";

	public static final String SEARCH_FORM_DATE_PREFIX = "#";
	public static final String SEARCH_FORM_DATE_SEPARATOR = "_";
	public static final String SEARCH_FORM_CONSTANT_KEY = "_CONSTANT_";

	public static final String PAGINATION_URL_PREFIX = "_PAGE_COUNT_";

	public static final String USER_AGENT = "Mozilla/5.0 (X11; Ubuntu; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/79.0.3945.130 Safari/537.36 RuxitSynthetic/1.0 v4818569895 t38550";
	public static final String Custom_USER_AGENT = "Clarivate-Mozilla/5.0 (X11; Ubuntu; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/79.0.3945.130 Safari/537.36 RuxitSynthetic/1.0 v4818569895 t38550";

	public static final String TEMPORARY = "tmp";

	public static Boolean APPLICATION_SHUTDOWN = Boolean.FALSE;

	public static final String UPDATED_VERSION_OF_FILE = "Updated version of the file";
	public static final String NEW_FILE = "NEW FILE";

	public static final String AUTOSCROLL_TEXT = "text";
	public static final String AUTOSCROLL_BUTTON = "button";

	public static final String ON_CLICK_NEW_TAB = "new_tab";
	public static final String ON_CLICK_SAME_TAB = "same_tab";
	public static final String ON_CLICK_OVERLAY = "overlay";
	public static final String ON_CLICK_DOWNLOAD = "download";
	public static final String ON_CLICK_DOWNLOAD_NEW_WINDOW = "download_new_window";
	public static final String IFRAME = "iframe";
	public static final String ON_CLICK_HOVER = "hover_download";
	public static final String ON_CLICK_PAGE_NAVIGATION = "page_navigation";
	public static final String ON_CLICK_FILTER = "filter";

	public static final String DEFAULT_PAGE_ORIENTATION = "default";
	public static final String PORTRAIT_PAGE_ORIENTATION = "portrait";
	public static final String LANDSCAPE_PAGE_ORIENTATION = "landscape";

	public static final String TAGS_ARE_NOT_DEFINE_PROPERLY = "Revise agent settings , tag definition does not match with source.";
	public static final String FILE_TYPE_NOT_FOUND = "Revise agent settings, file type does not match with source.";
	public static final String FAILED_TO_LOAD_CHROME_BROWSER = "Failed to load chrome browser.";
	public static final String PAGE_LOAD_TIME_OUT = "Web page is taking too long to load.";
	public static final String LOGIN_FAILURE = "Login failed. Please check username/password.";
	public static final String INVALID_SOURCE_URL = "Invalid source URL.";

	public static final String SCRAPING_STATUS_Failed = "Failed";
	public static final String SCRAPING_STATUS_Completed = "Completed";
	public static final String SCRAPING_STATUS_PARTIAL = "Partial";
	public static final String SCRAPING_STATUS_RUNNING = "Running";
	public static final String SCRAPING_STATUS_INQUEUE = "In Queue";
	public static final String SCRAPING_STATUS_ABORTED = "Aborted";
	public static final String SCRAPING_STATUS_PARTIAL_COMPLETE = "Partially Completed";
	public static final String SCRAPING_STATUS_TIMED_OUT = "Timed Out";

	public static final String CONTROL_TYPE_CALENDAR = "calendar";
	public static final String CONTROL_TYPE_TEXT_BOX = "textBox";
	public static final String CONTROL_TYPE_ITERATOR = "iterator";
	public static final String CONTROL_TYPE_PAGINATOR = "paginator";
	public static final String CONTROL_TYPE_RANGE = "range";
	public static final String API_END_POINT = "api_end_point";

	public static final String FILE_NAME_USER_PREFERENCE_HEADER = "#header";
	public static final String FILE_NAME_USER_PREFERENCE_DESCRIPTION = "#description";
	public static final String FILE_NAME_USER_PREFERENCE_URL = "#urlUniquePart";

	public static final int MULTIPART_REQUEST_VALID_RESPONSE_CODE = 206;

	/*
	 * WEBSCRAPE-3043
	 */
	public static final String HOST_PATH_TAG_VALUE_IDENTIFIER = "{siteNav_TagsInclude-%level%}";
	public static final String HOST_PATH_TAG_VALUE_IDENTIFIER_WITHOUT_LEVEL = "{siteNav_TagsInclude";
	public static final String LEVEL_IDENTIFIER = "%level%";
	public static final String DRAFT = "draft";

	/*
	 * WEBSCRAPE-3644
	 */
	public static final String CHROME_TEMP_DOWNLOAD_FOLER = "chrome_temp";
}
