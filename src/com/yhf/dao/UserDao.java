package com.yhf.dao;



import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.yhf.domain.Paging;
import com.yhf.domain.User;
import com.yhf.txl.utils.StringUtil;

public class UserDao extends BasicDao{
	//获取用户 信息
	public User getUserInfo(String name,String password) {
		String sql="select id,classId,name,password,sex,iphone,xinzuo,birthday,liuyan from t_user where name='" + name + "'and password='" + password + "'";
		try(ResultSet resultSet=query(sql)){
			
			if(resultSet.next()) {
				User user=new User();
				user.setId(resultSet.getInt("id"));
				user.setClassId(resultSet.getInt("classId"));
				user.setName(resultSet.getString("name"));
				user.setPassword(resultSet.getString("password"));
				user.setSex(resultSet.getString("sex"));
				user.setIphone(resultSet.getString("iphone"));
				user.setXinzuo(resultSet.getString("xinzuo"));
				user.setBirthday(resultSet.getString("birthday"));
				user.setLiuyan(resultSet.getString("liuyan"));
				return user;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public User getUserInfoByIphone(String iphone,String password) {
		String sql="select id,classId,name,password,sex,iphone,xinzuo,birthday,liuyan from t_user where iphone='" + iphone + "'and password='" + password + "'";
		try(ResultSet resultSet=query(sql)){
			
			if(resultSet.next()) {
				User user=new User();
				user.setId(resultSet.getInt("id"));
				user.setClassId(resultSet.getInt("classId"));
				user.setName(resultSet.getString("name"));
				user.setPassword(resultSet.getString("password"));
				user.setSex(resultSet.getString("sex"));
				user.setIphone(resultSet.getString("iphone"));
				user.setXinzuo(resultSet.getString("xinzuo"));
				user.setBirthday(resultSet.getString("birthday"));
				user.setLiuyan(resultSet.getString("liuyan"));
				return user;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	//查看数据库中含有名字
	/*public boolean getUserName(String name) {
		String sql="select name from t_user where name='" + name + "'";
		try(ResultSet resultSet=query(sql)){
			if(resultSet.next()) {				
				return true;
			}			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}*/
	//查找电话号码
	public boolean getUserIphone(String iphone) {
		String sql="select iphone from t_user where iphone='" + iphone + "'";
		try(ResultSet resultSet=query(sql)){
			if(resultSet.next()) {				
				return true;
			}			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	//添加用户信息
	public boolean addUser(User user) {
		String sql=null;
		if(!StringUtil.isEmpty(user.toString())) {
			sql="insert into t_user(classId,name,password,sex,iphone,xinzuo,birthday,liuyan) "
					+ "values('"+user.getClassId()+"','"+user.getName()+"','"+user.getPassword()+"'"
							+ ",'"+user.getSex()+"','"+user.getIphone()+"','"+user.getXinzuo()+"'"
									+ ",'"+user.getBirthday()+"','"+user.getLiuyan()+"')";			
		}		
		return update(sql);
	}
	
	//返回用户列表
	public List<User> getUserList(User user,Paging paging){
		List<User> list=new ArrayList<User>();
		String sql="select * from t_user";
		if(!StringUtil.isEmpty(user.getName())) {
			sql+=" and name like '%"+user.getName()+"%'";
		}
		if(user.getClassId()!=0) {
			sql+=" and classId = "+user.getClassId();
		}
		/*if(user.getId()!=0) {
			sql+=" and id ="+user.getId();
		}*/
		
		sql+=" limit "+paging.getPageStart()+" , "+paging.getPageSize();
		try(ResultSet resultSet=query(sql.replaceFirst("and", "where"))){
			while(resultSet.next()) {
				User user2=new User();
				user2.setId(resultSet.getInt("id"));
				user2.setClassId(resultSet.getInt("classid"));
				user2.setName(resultSet.getString("name"));
				user2.setPassword(resultSet.getString("password"));
				user2.setSex(resultSet.getString("sex"));
				user2.setIphone(resultSet.getString("iphone"));
				user2.setXinzuo(resultSet.getString("xinzuo"));
				user2.setBirthday(resultSet.getString("birthday"));
				user2.setLiuyan(resultSet.getString("liuyan"));
				list.add(user2);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	//返回用户信息数目
	public int getUserListNum(User user) {
		int num=0;
		String sql="select count(*) as num from t_user";
		if(!StringUtil.isEmpty(user.getName())) {
			sql+=" and name like '%"+user.getName()+"%'";
		}
		if(user.getClassId()!=0) {
			sql+=" and classId = "+user.getClassId();
		}
		if(!StringUtil.isEmpty(user.getIphone())) {
			sql+=" and Iphone = "+user.getIphone();
		}
		/*if(user.getId()!=0) {
			sql+=" and id ="+user.getId();
		}*/
		try(ResultSet resultSet=query(sql.replaceFirst("and", "where"))){
			while (resultSet.next()) {
				num=resultSet.getInt("num");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return num;
		
	}
	//更新用户信息
	public boolean editUser(User user) {
		String sql="update t_user set ClassId='"+user.getClassId()+"',"
				+ "name='"+user.getName()+"',sex='"+user.getSex()+"',"
				+ "xinzuo='"+user.getXinzuo()+"',"
				+ "birthday='"+user.getBirthday()+"',liuyan='"+user.getLiuyan()+"' where id= "+user.getId();
				
		return 	update(sql);							
	}
	
	public boolean editUserByIphone(User user) {
		String sql="update t_user set ClassId='"+user.getClassId()+"',"
				+ "name='"+user.getName()+"',sex='"+user.getSex()+"'"
				+ ",xinzuo='"+user.getXinzuo()+"',birthday='"+user.getBirthday()+"'"
				+ ",liuyan='"+user.getLiuyan()+"' where iphone='"+user.getIphone()+"'";
				System.out.println("sql:"+sql);
		return 	update(sql);							
	}
	
	public boolean deleteUser(String ids) {

		String sql = "delete from t_user where id in ( " + ids + " )";
		return update(sql);
	}
	public User getUserById(int id) {
		String sql="select * from t_user where id="+id;
		User user=null;
		try(ResultSet resultSet=query(sql)){
			user=new User();
			if(resultSet.next()) {
				user.setId(resultSet.getInt("id"));
				user.setClassId(resultSet.getInt("classId"));
				user.setName(resultSet.getString("name"));
				user.setPassword(resultSet.getString("password"));
				user.setSex(resultSet.getString("sex"));
				user.setIphone(resultSet.getString("iphone"));
				user.setXinzuo(resultSet.getString("xinzuo"));
				user.setBirthday(resultSet.getString("birthday"));
				user.setLiuyan(resultSet.getString("liuyan"));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return user;
	}
	//修改密码
	public boolean modifyPassword(User user, String newPassword) {
		String sql = "update t_user set password = '" + newPassword + "' where id = '" + user.getId()
				+ "'";
		return update(sql);
	}
	
}
