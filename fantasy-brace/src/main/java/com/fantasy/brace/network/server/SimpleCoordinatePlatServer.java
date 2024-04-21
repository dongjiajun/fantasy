package com.fantasy.brace.network.server;

import com.fantasy.brace.applicable.graphic.plat.AbstractNetWorkAccessiblePlat;
import com.fantasy.brace.math.coordinate.Coordinate;
import com.fantasy.brace.network.handler.UserConnectHandler;

import java.io.IOException;
import java.net.Socket;

public class SimpleCoordinatePlatServer extends SimpleNetWorkAccessiblePlatServer {

    private Coordinate<?> coordinate;


    public SimpleCoordinatePlatServer(int port, AbstractNetWorkAccessiblePlat netWorkAccessiblePlat, String classType, Coordinate<?> coordinate) throws IOException {
        super(port, netWorkAccessiblePlat, classType);
        this.coordinate = coordinate;
    }

    public Coordinate<?> getCoordinate() {
        return coordinate;
    }

    private void execute() throws IOException {
        System.out.println(getClassType() + "的用户服务器已启动....");
        for (; ; ) {
            Socket sock = getServerSocket().accept();
            if (getNetWorkAccessiblePlat().addIntoAccessList(sock.getInetAddress().toString() + ":" + sock.getPort(), sock)) {
                // TODO: 2023/9/30 待替换为统一的池模式处理，不允许独立开线程，以方便资源管理 ...
                new Thread(new UserConnectHandler(sock,
                        getClassType(),
                        getNetWorkAccessiblePlat(),
                        coordinate)).start();
            } else {
                System.out.println("注册用户到连接监听过程中异常");
            }
        }
    }

    @Override
    public void run() {
        try {
            execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
