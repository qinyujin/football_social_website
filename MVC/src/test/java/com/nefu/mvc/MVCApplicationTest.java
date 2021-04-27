package com.nefu.mvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nefu.mvc.component.RedisUtil;
import com.nefu.mvc.component.TransferJson;
import com.nefu.mvc.entity.*;
import com.nefu.mvc.mapper.*;
import com.nefu.mvc.service.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

/**
 * @author :覃玉锦
 * @create :2021-04-09 10:44:00
 */
@SpringBootTest
class MVCApplicationTest {

    private final String ROLE_PERMISSION_KEY = "rolePermissionList";

    @Autowired
    private PermissionMapper permissionMapper;

    @Autowired
    private PermissionService permissionService;

    @Autowired
    private RolePermissionMapper RolePermissionMapper;

    @Autowired
    private RolePermissionService rolePermissionService;

    @Autowired
    private ChatRoomService chatRoomService;

    @Autowired
    private ChatRoomUserMapper crum;

    @Autowired
    private ChatRoomUserService crums;

    @Autowired
    private TransferJson transferJson;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private VideoService videoService;

    @Autowired
    private CommentService commentService;

    @Autowired
    private MoodMapper moodMapper;

    @Autowired
    private MoodService moodService;

    @Autowired
    private FriendsMapper friendsMapper;

    @Autowired
    private FriendsService friendsService;

    @Autowired
    private LocationService locationService;

    @Autowired
    private ActivityService activityService;

    @Autowired
    private ActivityUserService activityUserService;

    @Autowired
    private ScheduleService scheduleService;

    @Autowired
    private TeamService teamService;

    @Autowired
    private PlayerService playerService;

    @Autowired
    private NationService nationService;

    @Autowired
    private StyleService styleService;

    @Autowired
    private BasicInfoService basicInfoService;

    @Autowired
    private MessageService messageService;

    @Autowired
    private UserRoleService userRoleService;

    @Autowired
    private UserService userService;

    @Autowired
    private RedisUtil redisUtil;

    @Test
    void test1(){
        /*List<Map<Integer,List<Integer>>> res = new ArrayList<>();
        Map<Integer,List<Integer>> map = new HashMap<>();
        map.put(1, Arrays.asList(1,2,3));
        map.put(2, Arrays.asList(1));
        System.out.println(map);
        for (Map.Entry<Integer, List<Integer>> entry : map.entrySet()) {
            //形如1:{1,2,3}
            List<Object> temp = new ArrayList<>();
            for (Integer v : entry.getValue()) {
                temp.add(v);
            }
            redisUtil.lSet(ROLE_PERMISSION_KEY+":"+entry.getKey(),temp,60*60*24,true);
        }*/
        List<Object> res = redisUtil.lGet(ROLE_PERMISSION_KEY + ":" + "1", 0, -1);
        List<Object> res1 = redisUtil.lGet(ROLE_PERMISSION_KEY + ":" + "2", 0, -1);
        System.out.println(res);
        System.out.println((int)res.get(0));
        System.out.println(res1);
    }

    @Test
    void test2(){
        String res = "[{\"id\":3,\"name\":\"张三\",\"num\":\"001\",\"password\":\"STbffk4gTxz4KRmT8fCUfFgPKcs4m5soLhtcKhCViC6BYAH5tw6Hulm+BHYVsBEuHvfqeTaekK9WvcKVVtLhWK7KRRmbcrKL2I7CMajM+xwKYvCvyPz3D4XF7u6OMhCyQiIAaSPQSh3JORV9vvvtuQ/AhJzo1yvvvfui0pmku58=\",\"icon\":\"0213\"},{\"id\":4,\"name\":\"王雷\",\"num\":\"002\",\"password\":\"RoW4qvz0SDfKywQyyO8cWmx4VhH8hHDUutxYbFeNAruHw2cm2MK5mbaBnQNbhs82TEyQdQs7T38Du25JWhF4UVQGYS3zn7OyGXCjJ6tupnIg+HwB+8cOd6TFcIZFm8WalN8Vb/9LkLV3Glhe/uU92OOuaByI0wOwasseAz4nBGI=\",\"icon\":\"D://xxx\"}]";
        List<User> o = (List<User>) transferJson.transferToClz(res, List.class);
        System.out.println(o);
        System.out.println(o.size());
        //不能使用增强for和迭代器
        for (int i = 0; i < o.size(); i++) {
            System.out.println(o.get(i));
        }
    }

    @Test
    void videoTest(){
        Video video = new Video();
        video.setId(1);
        video.setVideoTitle("巴萨VS皇马");
        video.setVideoThumbnail("D://xxx");
        video.setVideoDuration(5400);
        video.setVideoCreated(new Date());
        video.setVideoDescription("这是西班牙4.12日的一场国家德比");
        video.setVideoViewTimes(1200);
        video.setVideoState(0);
        video.setVideoAuthor("梅西");
        video.setVideoLink("https://v.qq.com/live/p/topic/109043/index.html");

        videoService.saveVideo(video);
//        videoService.deleteVideo(1);
//        videoService.updateVideo(video);
//        System.out.println(videoService.getVideos());
//        System.out.println(videoService.getVideoById(1));
    }

    @Test
    void redisUTest(){
        final String permissionKey = "permissionList";
        final String rolePermissionKey = "rolePermissionList";

        /*List<Object> permissionList = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            permissionList.add(new Permission(i+1,"p"+i,"xxx","GET/POST/PATCH/DELETE"));
        }
        redisUtil.lSet(permissionKey,permissionList,60*60*24,true);*/
        /*List<Object> permissionList = redisUtil.lGet(permissionKey, 0, -1);
        System.out.println(permissionList);
        System.out.println(permissionList.size());
        System.out.println(permissionList.get(0));
        Permission p = (Permission) permissionList.get(0);
        System.out.println(p.getMethod());*/

        List<Object> objects = redisUtil.lGet(permissionKey, 0, -1);
        System.out.println(objects == null);
        System.out.println(objects.size());
    }

    @Test
    void permissionTest(){
        Permission p = new Permission(3,"测试权限1","fjisjfis","fjsjfis");
//        permissionService.savePermission(p);
//        permissionService.deletePermission(3);
//        permissionService.updatePermission(p);
        System.out.println(permissionService.getPermissions());
        System.out.println(permissionService.getPermissionById(3));
    }

    @Test
    void commentTest(){
        Comment comment = new Comment();
        comment.setId(1);
        comment.setVideoId(1);
        comment.setMoodId(1);
        comment.setCommenterId(1);
        comment.setCommentContent("我C罗不粘锅，都是队友太垃圾了");
        comment.setCommentTime(new Date());
        comment.setLikeNum(2000);

        commentService.saveComment(comment);
//        commentService.deleteComment(1);
//        commentService.updateCommet(comment);
        System.out.println(commentService.getComments());
        System.out.println(commentService.getCommentById(1));
    }

    @Test
    void chatRoomTest(){
        /*ChatRoom cr = new ChatRoom();
        cr.setId(1);
        cr.setCategoryId(0);
        cr.setSpecificId(1);
        cr.setNotice("这是一个充满惊喜的群");
        cr.setManagerId(1);
        chatRoomService.saveChatRoom(cr);
//        chatRoomService.deleteChatRoom(1);
//        chatRoomService.updateChatRoom(cr);
        System.out.println(chatRoomService.getChatRooms());
        System.out.println(chatRoomService.getChatRoomById(1));*/

        List<Integer> c = crum.getRoomIdByUid(1);
        System.out.println(c);
        ChatRoomUser cru = new ChatRoomUser(4,1,3);
        System.out.println(crums.saveChatRoomUser(cru));
    }

    @Test
    void MoodTest(){
        Mood mood = new Mood();
        mood.setId(1);
        //字节形式
        mood.setContent("今天看到一个帅哥，长得很像C罗，搞得我脸红心跳的".getBytes());
        mood.setLikeCount(200);
        mood.setReleaseTime(new Date());
        mood.setUserId(2);

//        moodService.saveMood(mood);
//        moodService.deleteMood(1);
//        moodService.updateMood(mood);
        System.out.println(moodService.getMoods());
        System.out.println(moodService.getMoodById(1));
    }

    @Test
    void friendsTest(){
        /*Friends friends = new Friends();
        friends.setId(1);
        friends.setUseraId(1);
        friends.setUserbId(2);
        friends.setState(1);
//        friendsService.saveFriends(friends);
        friendsService.deleteFriends(1);
//        friendsService.updateFriends(friends);
        System.out.println(friendsService.getUseraIdByUserbId(2));
        System.out.println(friendsService.getUserbIdByUseraId(1));*/

        Friends friends = new Friends();
        friends.setUseraId(1);
        friends.setUserbId(2);
        Friends existFriends = friendsMapper.getExistFriends(friends);
        System.out.println(existFriends);
    }

    @Test
    void locationTest(){
        Location l = new Location();
        l.setId(1);
        l.setGeoType(Location.POLITICAL_GEOGRAPHY);
        l.setLatitude("116.23128");
        l.setLongitude("40.22077");
        l.setGeoNote("北京，我最想去的城市，但是没什么缘分啊");
        locationService.saveLocation(l);
//        locationService.deleteLocation(1);
//        locationService.updateLocation(l);
        System.out.println(locationService.getLocations());
        System.out.println(locationService.getLocationById(1));
    }

    @Test
    void ActivityTest(){
        Activity activity = new Activity();
        activity.setId(1);
        activity.setGeoId(1);
        activity.setStartTime(new Date());
        activity.setEndTime(new Date(System.currentTimeMillis()+1000*60*60*2));
        activity.setDeposit(20d);
        activity.setSponsorId(1);
        activity.setLimitCount(20);

//        activityService.saveActivity(activity);
//        activityService.deleteActivity(1);
//        activityService.updateActivity(activity);
        System.out.println(activityService.getActivities());
        System.out.println(activityService.getActivityById(1));
        System.out.println(activityService.getActivitiesByUid(1));

        /*ActivityUser au = new ActivityUser();
        au.setId(1);
        au.setActivityId(1);
        au.setUserId(3);
//        activityUserService.saveActivityUser(au);
        activityUserService.deleteActivityUser(1);
//        activityUserService.updateActivityUser(au);
        System.out.println(activityUserService.getActivityIdByUid(2));
        System.out.println(activityUserService.getUidByActivityId(1));*/
    }

    @Test
    void ScheduleTest(){
        Schedule schedule = new Schedule();

        schedule.setId(1);
        schedule.setMatchDate(LocalDate.of(2021,4,16));
        schedule.setMatchTime(LocalDateTime.of(2021,4, 16, 18,0,0));
        schedule.setLeagueName("西甲");
        schedule.setHomeTeamName("巴塞罗那");
        schedule.setVisitorTeamName("尤文图斯");
        schedule.setCurrentHomeTeamScore(0);
        schedule.setCurrentVisitorTeamScore(0);

//        scheduleService.saveSchedule(schedule);
        scheduleService.deleteSchedule(1);
//        scheduleService.updateSchedule(schedule);
        System.out.println(scheduleService.getSchedules());
        System.out.println(scheduleService.getScheduleById(1));
    }

    @Test
    void teamTest(){
        Team team = new Team();
//        team.setId(1);
        team.setTeamNum(1);
        team.setTeamName("巴西队");
        team.setNationId(1);
        team.setCreateTime(LocalDate.now());
        team.setTeamSize(20);
        team.setHeadCoachId(1);
        team.setHonor("2003年打入亚洲杯");

        teamService.saveTeam(team);
//        teamService.deleteTeam(1);
//        teamService.updateTeam(team);
        System.out.println(teamService.getTeams());
        System.out.println(teamService.getTeamById(1));
    }

    @Test
    void playerTest(){
        Player player = new Player();
        player.setId(1);
        player.setPlayerNum(1);
        player.setPlayerName("王洁");
        player.setNationId(1);
        player.setTeamId(1);
        player.setHeight(180.2d);
        player.setWeight(72.2d);
        player.setAge(28);
        player.setNum(10);
        player.setPosition("前锋");
        player.setHistory("中超最佳射手");

//        playerService.savePlayer(player);
        playerService.deletePlayer(1);
//        playerService.updatePlayer(player);
        System.out.println(playerService.getPlayers());
        System.out.println(playerService.getPlayerById(1));
    }

    @Test
    void nationTest(){
        Nation nation = new Nation();
        nation.setId(1);
        nation.setName("美国");

//        nationService.saveNation(nation);
        nationService.deleteNation(1);
//        nationService.updateNation(nation);
        System.out.println(nationService.getNations());
        System.out.println(nationService.getNationById(1));
    }

    @Test
    void styleTest(){
        Style style = new Style();
        style.setId(1);
        style.setName("tiktak");
        style.setDescription("通过不断地传球保持良好的控球率，然后在传控的过程中寻找进球的机会");
        style.setTypicalTeam("巴塞罗那");

//        styleService.saveStyle(style);
        styleService.deleteStyle(1);
//        styleService.updateStyle(style);
        System.out.println(styleService.getStyles());
        System.out.println(styleService.getStyleById(1));
    }

    @Test
    void basicInfoTest(){
        BasicInfo basicInfo = new BasicInfo();
        basicInfo.setId(1);
        basicInfo.setCategoryId(1);
        basicInfo.setSpecificId(1);

//        basicInfoService.saveBasicInfo(basicInfo);
        basicInfoService.deleteBasicInfo(1);
//        basicInfoService.updateBasicInfo(basicInfo);
        System.out.println(basicInfoService.getBasicInfos());
        System.out.println(basicInfoService.getBasicInfoById(1));
        System.out.println(basicInfoService.getCidsBySid(1));
    }

    @Test
    void messageTest(){
        Message message = new Message();
        message.setId(1);
        message.setChatRoomId(1);
        message.setUserId(1);
        message.setContent("大家好，我叫aimer，来自贵州省安顺市紫云县。我刚上大学，希望以后大家多多担待");
        message.setMessageType(Message.TEXT);
        message.setSendTime(new Date());

//        messageService.saveMessage(message);
        messageService.deleteMessage(1);
//        messageService.updateMessage(message);
        System.out.println(messageService.getMessages());
        System.out.println(messageService.getMessageById(1));
    }

    @Test
    void userRoleTest(){
        //添加用户会默认给一个游客的角色
        /*User user = new User();
        user.setName("马尔扎哈");
        user.setNum("test999");
        user.setPassword("123456");

        userService.saveUser(user);*/

        //如果给用户赋予其他角色，那么会删除游客角色
        UserRole ur = new UserRole();
        ur.setUserId(15);
        ur.setRoleId(3);
        System.out.println(userRoleService.saveUserRole(ur));
    }
}