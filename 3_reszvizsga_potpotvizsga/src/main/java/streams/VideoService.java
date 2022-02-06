package streams;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class VideoService {
    private List<Video> videoList = new ArrayList<>();

    public void addVideo(Video video) {
        videoList.add(video);
    }

    public List<Video> findVideosByTitle(String titlePart) {
        return videoList.stream().filter(v -> v.getTitle().contains(titlePart)).sorted(Comparator.comparing(Video::getUploadDate).reversed()).toList();
    }

    public long countVideosWithHashTag(String hashTag) {
        return videoList.stream().filter(v -> v.getHashTags().contains(hashTag)).count();
    }

    public Video firstVideoByDate() {
        return videoList.stream().sorted(Comparator.comparing(Video::getUploadDate)).findFirst().orElseThrow(() -> new IllegalStateException("No videos in list!"));
    }

    public long sumOfVideoLengths() {
        return videoList.stream().mapToInt(Video::getLength).sum();
    }
}
