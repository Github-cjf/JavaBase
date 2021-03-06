package netty.timeServer;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.concurrent.atomic.AtomicInteger;
import lombok.extern.slf4j.Slf4j;

/**
 * 服务端的业务代码 继承自 ChannelHandlerAdapter
 */
@Slf4j
public class TimeServerHandler extends ChannelHandlerAdapter {

  private static AtomicInteger counter = new AtomicInteger();

  /**
   * 成功建立连接后, 读取服务端的消息
   */
  @Override
  public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
    ByteBuf buf = (ByteBuf) msg;
    byte[] req = new byte[buf.readableBytes()];
    buf.readBytes(req);
    String body = new String(req, StandardCharsets.UTF_8);

    log.info("server receive msg: body={} count={}", body, counter.incrementAndGet());

    String currentTime = "QUERY TIME ORDER".equalsIgnoreCase(body) ?
        LocalDateTime.now().toString() : "BAD ORDER";
    ByteBuf resp = Unpooled.copiedBuffer(currentTime.getBytes());
    ctx.write(resp);
  }

  /**
   * 因为是将消息队列中的消息放到缓冲数组中, 那么需要调用这个flush方法将消息发送至客户端
   */
  @Override
  public void channelReadComplete(ChannelHandlerContext ctx) {
    ctx.flush();
  }

  /**
   * 发生异常就调用该方法
   */
  @Override
  public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
    ctx.close();
  }
}
