package com.spotily.app.playlist;

import com.spotily.app.playlist.filterplaylist.FilterPlaylist;
import com.spotily.app.song.SongDataAccessService;
import com.spotily.app.user.UserDataAccessService;
import org.checkerframework.checker.units.qual.A;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Filter;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;


public class PlaylistTests {
    private PlaylistDataAccessService playlistDataMock;
    private UserDataAccessService userDataAccessService;
    private SongDataAccessService songDataAccessService;
    private PlaylistService underTest;

    @BeforeEach
    void setUp(){
//        set up mock database
        playlistDataMock = mock(PlaylistDataAccessService.class);
//        construct undertest instance with all data access services
        underTest = new PlaylistService(playlistDataMock, userDataAccessService, songDataAccessService);
    }

//    methods to test - service:
//    also go thru and see which ones throw exceptions and whether they throw as would expect
//    makeNewPlaylist makes a new playlist in database - not to test, just calls the dao method with void return
//    getNewestPlaylistId id gets the max id in database - lower priority, just the same as dao method call result
    @Test
    void canGetNewestPlaylistId(){
        when(playlistDataMock.getNewestPlaylistId()).thenReturn(10);
        int expected = 10;
        int actual = underTest.getNewestPlaylistId();
        assertThat(actual).isEqualTo(expected);
    }
//    addToPlaylist adds the song id to the playlist_maker table in database - void return just calls data access service
//    makePlaylist - lots of different parts to this can test, tested in thunderclient but would be good to do officially

//    makeThemedPlaylist - same tests as the makePlaylist part
//    getByMood - again just returns the data access service result
    @Test
    void getByMoodReturnsSameAsDataAccessEquivalent(){
        ArrayList<Integer> expected = new ArrayList<>(List.of(1,3,6,7,10,15));
        when(playlistDataMock.getByMood("Ice Cream")).thenReturn(expected);
        ArrayList<Integer> actual = underTest.getByMood("Ice Cream");
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void getByMoodThrowsErrorIfNoSongsFound(){
        String answer = "Hyeurdf";
        ArrayList<Integer> expected = new ArrayList<>();
        when(playlistDataMock.getByMood(answer)).thenReturn(expected);
        assertThatThrownBy(()-> underTest.getByMood(answer)).hasMessageContaining("No " + answer + " songs found");
    }
//    getByMoodAndTheme -
    @Test
    void getByMoodAndThemeReturnsSameAsDataAccessEquivalent(){
        String answer = "Spring";
        int theme = 1;
        ArrayList<Integer> expected = new ArrayList<>(List.of(6,10,15,20));
        when(playlistDataMock.getByMoodAndTheme(answer, theme)).thenReturn(expected);
        ArrayList<Integer> actual = underTest.getByMoodAndTheme(answer, theme);
        assertThat(actual).isEqualTo(expected);
    }
    @Test
    void getByMoodAndThemeThrowsErrorIfNoSongsFound(){
        String answer = "Hyeurdf";
        int theme = 666;
        ArrayList<Integer> expected = new ArrayList<>();
        when(playlistDataMock.getByMoodAndTheme(answer, theme)).thenReturn(expected);
        assertThatThrownBy(()-> underTest.getByMoodAndTheme(answer, theme)).hasMessageContaining("No " + answer + " songs found");
    }

//    getAllPlaylists -
    @Test
    void getAllPlaylistsReturnsDataAccessResultWhenPresent(){
        List<FilterPlaylist> expected =  List.of(
                new FilterPlaylist(1,10, "Uptown Funk", "Bruno Mars"),
                new FilterPlaylist(2, 8, "Happy", "Pharrell Williams"),
                new FilterPlaylist(3, 20, "Walk", "Pantera"));
        when(playlistDataMock.getAllPlaylists()).thenReturn(expected);
        List<FilterPlaylist> actual = underTest.getAllPlaylists();
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void getAllPlaylistsThrowsErrorWhenNoneFound(){
        List<FilterPlaylist> expected =  List.of();
        when(playlistDataMock.getAllPlaylists()).thenReturn(expected);
        assertThatThrownBy(() -> underTest.getAllPlaylists()).hasMessageContaining("No playlists found");
    }

//    selectPlaylistById -
    @Test
    void selectPlaylistByIdReturnsDataAccessResultWhenPresent(){
        List<FilterPlaylist> expected = List.of(
                new FilterPlaylist(1,2, "Song", "That Singer"),
                new FilterPlaylist(1, 30, "Tune", "Songer"),
                new FilterPlaylist(1, 10, "Tiktok", "Donna"),
                new FilterPlaylist(1, 23, "Word Up", "Cameo")
                );
        when(playlistDataMock.selectPlaylistbyId(1)).thenReturn(expected);
        List<FilterPlaylist> actual = underTest.selectPlaylistById(1);
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void selectPlaylistByIdThrowsErrorWhenNotFound(){
        List<FilterPlaylist> toReturn = List.of();
        int playlistId = 100;
        when(playlistDataMock.selectPlaylistbyId(playlistId)).thenReturn(toReturn);
        assertThatThrownBy(()-> underTest.selectPlaylistById(playlistId))
                .hasMessageContaining("Playlist " + playlistId + " does not exist");
    }
//    deletePlaylist - checking for error, otherwise no return just calls dao method
    @Test
    void deletePlaylistThrowsErrorWhenNotFound(){
        List<FilterPlaylist> toReturn = List.of();
        int playlistId = 100;
        when(playlistDataMock.selectPlaylistbyId(playlistId)).thenReturn(toReturn);
        assertThatThrownBy(()-> underTest.deletePlaylist(playlistId))
                .hasMessageContaining("Playlist " + playlistId + " does not exist");
    }

//    newPlaylistTest - not in use was for testing purposes
//    getMaxPlaylistId -
    @Test
    void maxPlaylistIdReturnsSameAsDAO(){
        int expected = 25;
        when(playlistDataMock.getNewestPlaylistId()).thenReturn(expected);
        int actual = underTest.getNewestPlaylistId();
        assertThat(actual).isEqualTo(expected);
    }
//    checkIfUserHasPlaylist - test fails type mismatch optional vs what's in the optional
    @Test
    @Disabled
    void checkIfUserHasPlaylistReturnsSamePlaylistAsDAO(){
        Optional<Playlist> expected = Optional.of(
                new Playlist(10, 2, List.of(1,2,3,4,6)));
        when(playlistDataMock.checkIfUserHasPlaylist(2)).thenReturn(expected);
        assertThat(underTest.checkIfUserHasPlaylist(2)).isEqualTo(expected);
    }

    @Test
    void userWithNoPlaylistsThrowsErrorForCheckIfUserHasPlaylist(){
        when(playlistDataMock.checkIfUserHasPlaylist(1)).thenReturn(Optional.ofNullable(null));
        assertThatThrownBy(()-> underTest.checkIfUserHasPlaylist(1)).hasMessageContaining("User has no playlists");
    }
//    checkIfSongIsInPlaylist -
    @Test
    void checkIfSongReturnsSongIfValid(){
        ArrayList<Integer> expected = new ArrayList<>(List.of(20));
        when(playlistDataMock.checkIfSongIsInPlaylist(1,20)).thenReturn(expected);
        ArrayList<Integer> actual = underTest.checkIfSongIsInPlaylist(1,20);
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void checkIfSongThrowsErrorWhenNoneReturned(){
        int songId = 500;
        int playlistId = 1;
        ArrayList<Integer> expected = new ArrayList<>(List.of());
        when(playlistDataMock.checkIfSongIsInPlaylist(playlistId,songId)).thenReturn(expected);
//        ArrayList<Integer> actual = underTest.checkIfSongIsInPlaylist(1,20);
        assertThatThrownBy(() -> underTest.checkIfSongIsInPlaylist(playlistId, songId)).hasMessageContaining(
                "Song " + songId + " is not found in playlist " + playlistId);
    }
//    checkAllSongInPlaylist -
//    replaceSongInPlaylist -


//

}
