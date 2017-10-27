package com.lanou.action;

import com.lanou.domain.Area;
import com.lanou.domain.Server;
import com.opensymphony.xwork2.ActionSupport;
import net.sf.json.JSONArray;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by dllo on 17/10/27.
 */
public class CitySelectAction extends ActionSupport {
//    用来存储所有大区的集合
    private List<Area> areaList;

//    定义变量:用来接收前段传过来的那个大区
    private int index;
    private List<Server> servers;

    public CitySelectAction() {
        areaList = new ArrayList<>();
//        假数据:创建5个大区
        for (int i = 0; i < 5; i++) {
            Area area = new Area();
            area.setId(i);
            area.setAname("网通" + i + "区");
//            在每个大区中创建10个服务器
            List<Server> servers = new ArrayList<>();
            for (int j = 1; j < 10; j++) {
                Server server = new Server();
                server.setId(j);
                server.setSname(i + "区" + "小霸王" + j + "号");
                servers.add(server);
            }
//            把服务器放到大区对象中
            area.setServers(servers);
            areaList.add(area);
        }
    }

    public String playGame() {
        return SUCCESS;
    }

    public String getServer() throws IOException {
//        返回json数据
//        获取选择服务器大区中服务器的数据
        List<Server> servers = areaList.get(index).getServers();
//        把集合数据转换成JSON数据,写入响应体中
        String json = JSONArray.fromObject(servers).toString();
        HttpServletResponse response = ServletActionContext.getResponse();
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write(json);
        return null;
    }


//    使用struts2的方法向客户端返回json数据
    public String getServerJson(){
//        给需要转换的数据类兑现赋值,实际上就是获取大区中所有服务器的集合
//        注意:数据类型必须是成员变量
        servers = areaList.get(index).getServers();

        return SUCCESS;
    }



    public List<Area> getAreaList() {
        return areaList;
    }

    public void setAreaList(List<Area> areaList) {
        this.areaList = areaList;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public List<Server> getServers() {
        return servers;
    }

    public void setServers(List<Server> servers) {
        this.servers = servers;
    }
}
