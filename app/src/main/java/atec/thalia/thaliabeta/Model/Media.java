package atec.thalia.thaliabeta.Model;

public class Media {
	
	public static final int IMAGE=0,VIDEO=1,SOUND=2;
	
	int type_media;
	
	String path_file;

	public Media(int type_media, String path_file) {
		super();
		this.type_media = type_media;
		this.path_file = path_file;
	}

	public int getType_media() {
		return type_media;
	}

	public void setType_media(int type_media) {
		this.type_media = type_media;
	}

	public String getPath_file() {
		return path_file;
	}

	public void setPath_file(String path_file) {
		this.path_file = path_file;
	}
	
	

}
