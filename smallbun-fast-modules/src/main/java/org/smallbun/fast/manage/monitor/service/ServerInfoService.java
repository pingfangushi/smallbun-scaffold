package org.smallbun.fast.manage.monitor.service;

import lombok.Data;
import org.smallbun.framework.toolkit.ArithUtil;
import org.smallbun.framework.toolkit.DateUtil;
import org.smallbun.framework.toolkit.IpUtil;
import oshi.SystemInfo;
import oshi.hardware.CentralProcessor;
import oshi.hardware.CentralProcessor.TickType;
import oshi.hardware.GlobalMemory;
import oshi.hardware.HardwareAbstractionLayer;
import oshi.software.os.FileSystem;
import oshi.software.os.OSFileStore;
import oshi.software.os.OperatingSystem;
import oshi.util.Util;

import java.lang.management.ManagementFactory;
import java.net.UnknownHostException;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;

/**
 * 服务器相关信息
 *
 * @author wangchl
 */
@Data
public class ServerInfoService {

	private static final int OSHI_WAIT_SECOND = 1000;

	/**
	 * CPU相关信息
	 */
	private CpuInfo cpu = new CpuInfo();

	/**
	 * 內存相关信息
	 */
	private MemInfo mem = new MemInfo();

	/**
	 * JVM相关信息
	 */
	private JvmInfo jvm = new JvmInfo();

	/**
	 * 服务器相关信息
	 */
	private SysInfo sys = new SysInfo();

	/**
	 * 磁盘相关信息
	 */
	private List<SysFileInfo> sysFiles = new LinkedList<>();

	public void copyTo() {
		try {
			SystemInfo si = new SystemInfo();
			HardwareAbstractionLayer hal = si.getHardware();
			setCpuInfo(hal.getProcessor());
			setMemInfo(hal.getMemory());
			setSysInfo();
			setJvmInfo();
			setSysFiles(si.getOperatingSystem());
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}

	}

	/**
	 * 设置CPU信息
	 */
	private void setCpuInfo(CentralProcessor processor) {
		// CPU信息
		long[] prevTicks = processor.getSystemCpuLoadTicks();
		Util.sleep(OSHI_WAIT_SECOND);
		long[] ticks = processor.getSystemCpuLoadTicks();
		long nice = ticks[TickType.NICE.getIndex()] - prevTicks[TickType.NICE.getIndex()];
		long irq = ticks[TickType.IRQ.getIndex()] - prevTicks[TickType.IRQ.getIndex()];
		long softirq = ticks[TickType.SOFTIRQ.getIndex()] - prevTicks[TickType.SOFTIRQ.getIndex()];
		long steal = ticks[TickType.STEAL.getIndex()] - prevTicks[TickType.STEAL.getIndex()];
		long cSys = ticks[TickType.SYSTEM.getIndex()] - prevTicks[TickType.SYSTEM.getIndex()];
		long user = ticks[TickType.USER.getIndex()] - prevTicks[TickType.USER.getIndex()];
		long iowait = ticks[TickType.IOWAIT.getIndex()] - prevTicks[TickType.IOWAIT.getIndex()];
		long idle = ticks[TickType.IDLE.getIndex()] - prevTicks[TickType.IDLE.getIndex()];
		long totalCpu = user + nice + cSys + idle + iowait + irq + softirq + steal;
		cpu.setCpuNum(processor.getLogicalProcessorCount());
		cpu.setTotal(totalCpu);
		cpu.setSys(cSys);
		cpu.setUsed(user);
		cpu.setWait(iowait);
		cpu.setFree(idle);
	}

	/**
	 * 设置内存信息
	 */
	private void setMemInfo(GlobalMemory memory) {
		mem.setTotal(memory.getTotal());
		mem.setUsed(memory.getTotal() - memory.getAvailable());
		mem.setFree(memory.getAvailable());
	}

	/**
	 * 设置服务器信息
	 */
	private void setSysInfo() {
		Properties props = System.getProperties();
		sys.setComputerName(IpUtil.getHostName());
		sys.setComputerIp(IpUtil.getHostIp());
		sys.setOsName(props.getProperty("os.name"));
		sys.setOsArch(props.getProperty("os.arch"));
		sys.setUserDir(props.getProperty("user.dir"));
	}

	/**
	 * 设置Java虚拟机
	 */
	private void setJvmInfo() throws UnknownHostException {
		Properties props = System.getProperties();
		jvm.setTotal(Runtime.getRuntime().totalMemory());
		jvm.setMax(Runtime.getRuntime().maxMemory());
		jvm.setFree(Runtime.getRuntime().freeMemory());
		jvm.setVersion(props.getProperty("java.version"));
		jvm.setHome(props.getProperty("java.home"));
	}

	/**
	 * 设置磁盘信息
	 */
	private void setSysFiles(OperatingSystem os) {
		FileSystem fileSystem = os.getFileSystem();
		OSFileStore[] fsArray = fileSystem.getFileStores();
		for (OSFileStore fs : fsArray) {
			long free = fs.getUsableSpace();
			long total = fs.getTotalSpace();
			long used = total - free;
			SysFileInfo sysFile = new SysFileInfo();
			sysFile.setDirName(fs.getMount());
			sysFile.setSysTypeName(fs.getType());
			sysFile.setTypeName(fs.getName());
			sysFile.setTotal(convertFileSize(total));
			sysFile.setFree(convertFileSize(free));
			sysFile.setUsed(convertFileSize(used));
			sysFile.setUsage(ArithUtil.mul(ArithUtil.div(used, total, 4), 100));
			sysFiles.add(sysFile);
		}
	}

	/**
	 * 字节转换
	 *
	 * @param size 字节大小
	 * @return 转换后值
	 */
	private String convertFileSize(long size) {
		long kb = 1024;
		long mb = kb * 1024;
		long gb = mb * 1024;
		if (size >= gb) {
			return String.format("%.1f GB", (float) size / gb);
		} else if (size >= mb) {
			float f = (float) size / mb;
			return String.format(f > 100 ? "%.0f MB" : "%.1f MB", f);
		} else if (size >= kb) {
			float f = (float) size / kb;
			return String.format(f > 100 ? "%.0f KB" : "%.1f KB", f);
		} else {
			return String.format("%d B", size);
		}
	}
}

/**
 * CPU相关信息
 *
 * @author wangchl
 */
class CpuInfo {

	/**
	 * 核心数
	 */
	private int cpuNum;

	/**
	 * CPU总的使用率
	 */
	private double total;

	/**
	 * CPU系统使用率
	 */
	private double sys;

	/**
	 * CPU用户使用率
	 */
	private double used;

	/**
	 * CPU当前等待率
	 */
	private double wait;

	/**
	 * CPU当前空闲率
	 */
	private double free;

	public int getCpuNum() {
		return cpuNum;
	}

	public void setCpuNum(int cpuNum) {
		this.cpuNum = cpuNum;
	}

	public double getTotal() {
		return ArithUtil.round(ArithUtil.mul(total, 100), 2);
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public double getSys() {
		return ArithUtil.round(ArithUtil.mul(sys / total, 100), 2);
	}

	public void setSys(double sys) {
		this.sys = sys;
	}

	public double getUsed() {
		return ArithUtil.round(ArithUtil.mul(used / total, 100), 2);
	}

	public void setUsed(double used) {
		this.used = used;
	}

	public double getWait() {
		return ArithUtil.round(ArithUtil.mul(wait / total, 100), 2);
	}

	public void setWait(double wait) {
		this.wait = wait;
	}

	public double getFree() {
		return ArithUtil.round(ArithUtil.mul(free / total, 100), 2);
	}

	public void setFree(double free) {
		this.free = free;
	}
}

/**
 * 虚拟机相关信息
 *
 * @author wangchl
 */
class JvmInfo {

	/**
	 * 当前JVM占用的内存总数(M)
	 */
	private double total;

	/**
	 * JVM最大可用内存总数(M)
	 */
	private double max;

	/**
	 * JVM空闲内存(M)
	 */
	private double free;

	/**
	 * JDK版本
	 */
	private String version;

	/**
	 * JDK路径
	 */
	private String home;

	public double getTotal() {
		return ArithUtil.div(total, (1024 * 1024), 2);
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public double getMax() {
		return ArithUtil.div(max, (1024 * 1024), 2);
	}

	public void setMax(double max) {
		this.max = max;
	}

	public double getFree() {
		return ArithUtil.div(free, (1024 * 1024), 2);
	}

	public void setFree(double free) {
		this.free = free;
	}

	public double getUsed() {
		return ArithUtil.div(total - free, (1024 * 1024), 2);
	}

	public double getUsage() {
		return ArithUtil.mul(ArithUtil.div(total - free, total, 4), 100);
	}

	/**
	 * 获取JDK名称
	 */
	public String getName() {
		return ManagementFactory.getRuntimeMXBean().getVmName();
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getHome() {
		return home;
	}

	public void setHome(String home) {
		this.home = home;
	}

	/**
	 * JDK启动时间
	 */
	public String getStartTime() {
		return DateUtil.date2Str(DateUtil.getServerStartDate(), DateUtil.DATE_PATTERN_DETAIL);
	}

	/**
	 * JDK运行时间
	 */
	public String getRunTime() {
		return DateUtil.getDatePoor(DateUtil.getNowDate(), DateUtil.getServerStartDate());
	}
}

/**
 * 内存相关信息
 *
 * @author wangchl
 */
class MemInfo {
	/**
	 * 内存总量
	 */
	private double total;

	/**
	 * 已用内存
	 */
	private double used;

	/**
	 * 剩余内存
	 */
	private double free;

	public double getTotal() {
		return ArithUtil.div(total, (1024 * 1024 * 1024), 2);
	}

	public void setTotal(long total) {
		this.total = total;
	}

	public double getUsed() {
		return ArithUtil.div(used, (1024 * 1024 * 1024), 2);
	}

	public void setUsed(long used) {
		this.used = used;
	}

	public double getFree() {
		return ArithUtil.div(free, (1024 * 1024 * 1024), 2);
	}

	public void setFree(long free) {
		this.free = free;
	}

	public double getUsage() {
		return ArithUtil.mul(ArithUtil.div(used, total, 4), 100);
	}
}

/**
 * 系统文件相关信息
 *
 * @author wangchl
 */
class SysFileInfo {

	/**
	 * 盘符路径
	 */
	private String dirName;

	/**
	 * 盘符类型
	 */
	private String sysTypeName;

	/**
	 * 文件类型
	 */
	private String typeName;

	/**
	 * 总大小
	 */
	private String total;

	/**
	 * 剩余大小
	 */
	private String free;

	/**
	 * 已经使用量
	 */
	private String used;

	/**
	 * 资源的使用率
	 */
	private double usage;

	public String getDirName() {
		return dirName;
	}

	public void setDirName(String dirName) {
		this.dirName = dirName;
	}

	public String getSysTypeName() {
		return sysTypeName;
	}

	public void setSysTypeName(String sysTypeName) {
		this.sysTypeName = sysTypeName;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public String getTotal() {
		return total;
	}

	public void setTotal(String total) {
		this.total = total;
	}

	public String getFree() {
		return free;
	}

	public void setFree(String free) {
		this.free = free;
	}

	public String getUsed() {
		return used;
	}

	public void setUsed(String used) {
		this.used = used;
	}

	public double getUsage() {
		return usage;
	}

	public void setUsage(double usage) {
		this.usage = usage;
	}
}

/**
 * 系统相关信息
 *
 * @author wangchl
 */
class SysInfo {

	/**
	 * 服务器名称
	 */
	private String computerName;

	/**
	 * 服务器Ip
	 */
	private String computerIp;

	/**
	 * 项目路径
	 */
	private String userDir;

	/**
	 * 操作系统
	 */
	private String osName;

	/**
	 * 系统架构
	 */
	private String osArch;

	public String getComputerName() {
		return computerName;
	}

	public void setComputerName(String computerName) {
		this.computerName = computerName;
	}

	public String getComputerIp() {
		return computerIp;
	}

	public void setComputerIp(String computerIp) {
		this.computerIp = computerIp;
	}

	public String getUserDir() {
		return userDir;
	}

	public void setUserDir(String userDir) {
		this.userDir = userDir;
	}

	public String getOsName() {
		return osName;
	}

	public void setOsName(String osName) {
		this.osName = osName;
	}

	public String getOsArch() {
		return osArch;
	}

	public void setOsArch(String osArch) {
		this.osArch = osArch;
	}
}
