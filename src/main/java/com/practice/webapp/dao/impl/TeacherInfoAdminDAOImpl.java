package com.practice.webapp.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import com.practice.webapp.dao.TeacherInfoAdminDAO;
import com.practice.webapp.entity.teacher.TeacherBasicInfo;
import com.practice.webapp.entity.teacher.TeacherBasicInfoAdmin;

public class TeacherInfoAdminDAOImpl implements TeacherInfoAdminDAO {

	private DataSource dataSource;
	private Connection conn = null;
	private ResultSet rs = null;
	private PreparedStatement smt = null;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	public List<TeacherBasicInfoAdmin> getProTeacherInfoList() {
		String sql = "select teacher.TEA_PHOTO, teacher.TEA_CODE, teacher.TEA_LDAP, "
				+ "members.M_NAME, teacher.TEA_EN_NAME, class_post.POST_NAME, "
				+ "members.M_PHONE, members.M_EMAIL, teacher.Location, teacher.TEA_ABLE "
				+ "from teacher, members, class_post, members_class " + "where "
				+ "teacher.TEA_LDAP = members.M_LDAP and " + "members.M_LDAP = members_class.M_LDAP and "
				+ "members_class.M_POST_CODE = class_post.POST_CODE and " + "class_post.POST_CODE not like 'DB' and "
				+ "class_post.POST_CODE not like 'DC' and " + "class_post.POST_CODE not like 'AA' and "
				+ "teacher.TeacherType like 'T' " + "order by teacher.TEA_ABLE desc, teacher.TEA_SORT";
		return getList(sql);
	}

	@Override
	public List<TeacherBasicInfoAdmin> getPartTeacherInfoList() {
		String sql = "select teacher.TEA_PHOTO, teacher.TEA_CODE, teacher.TEA_LDAP, "
				+ "members.M_NAME, teacher.TEA_EN_NAME, class_post.POST_NAME, "
				+ "members.M_PHONE, members.M_EMAIL, teacher.Location, teacher.TEA_ABLE "
				+ "from teacher, members, class_post, members_class " + "where "
				+ "teacher.TEA_LDAP = members.M_LDAP and " + "members.M_LDAP = members_class.M_LDAP and "
				+ "members_class.M_POST_CODE = class_post.POST_CODE and " + "class_post.POST_CODE not like 'DB' and "
				+ "class_post.POST_CODE not like 'DC' and " + "class_post.POST_CODE not like 'AA' and "
				+ "teacher.TeacherType like 'B' " + "order by teacher.TEA_ABLE desc, teacher.TEA_SORT";
		return getList(sql);
	}

	private List<TeacherBasicInfoAdmin> getList(String sql) {
		List<TeacherBasicInfoAdmin> infoList = new ArrayList<TeacherBasicInfoAdmin>();

		try {
			conn = dataSource.getConnection();
			smt = conn.prepareStatement(sql);
			rs = smt.executeQuery();
			while (rs.next()) {
				TeacherBasicInfoAdmin info = new TeacherBasicInfoAdmin();
				info.setTeaPic(rs.getString("TEA_PHOTO"));
				info.setTeaCode(rs.getString("TEA_CODE"));
				info.setTeaLDAP(rs.getString("TEA_LDAP"));
				info.setTeaName(rs.getString("M_NAME"));
				info.setTeaENName(rs.getString("TEA_EN_NAME"));
				info.setTeaPos(rs.getString("POST_NAME"));
				info.setTeaTel(rs.getString("M_PHONE"));
				info.setTeaEmail(rs.getString("M_EMAIL"));
				info.setTeaLoc(rs.getString("Location"));
				info.setTeaAble(rs.getString("TEA_ABLE"));
				infoList.add(info);
			}
			rs.close();
			smt.close();

		} catch (SQLException e) {
			throw new RuntimeException(e);

		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
				}
			}
		}
		return infoList;
	}

	@Override
	public void update(TeacherBasicInfoAdmin updateInfo) {
		String sql1 = "Update members SET M_NAME = ?, M_PHONE = ?, M_EMAIL = ? " + "WHERE M_LDAP =?";
		String sql2 = "Update teacher SET TEA_EN_NAME = ?, Location = ? " + "WHERE TEA_LDAP =?";
		String sql3 = "Update members_class SET M_POST_CODE = ? " + "WHERE M_LDAP =?";

		try {
			PreparedStatement smt1 = null;
			PreparedStatement smt2 = null;
			PreparedStatement smt3 = null;
			conn = dataSource.getConnection();
			smt1 = conn.prepareStatement(sql1);
			smt1.setString(1, updateInfo.getTeaName());
			smt1.setString(2, updateInfo.getTeaTel());
			smt1.setString(3, updateInfo.getTeaEmail());
			smt1.setString(4, updateInfo.getTeaLDAP());
			smt1.executeUpdate();
			smt1.close();

			smt2 = conn.prepareStatement(sql2);
			smt2.setString(1, updateInfo.getTeaENName());
			smt2.setString(2, updateInfo.getTeaLoc());
			smt2.setString(3, updateInfo.getTeaLDAP());
			smt2.executeUpdate();
			smt2.close();

			smt3 = conn.prepareStatement(sql3);
			smt3.setString(1, updateInfo.getTeaPos());
			smt3.setString(2, updateInfo.getTeaLDAP());
			smt3.executeUpdate();
			smt3.close();

		} catch (SQLException e) {
			throw new RuntimeException(e);

		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
				}
			}
		}

	}

	@Override
	public void delete(TeacherBasicInfoAdmin deleteInfo) {

		String sql1 = "Update teacher, members_class SET members_class.M_POST_CODE = 'DZ' "
				+ "WHERE members_class.M_LDAP = teacher.TEA_LDAP and teacher.TEA_CODE = ?";
		String sql2 = "Update teacher, members_class SET teacher.TEA_ABLE = '0' "
				+ "WHERE members_class.M_LDAP = teacher.TEA_LDAP and teacher.TEA_CODE = ?";

		try {
			conn = dataSource.getConnection();
			smt = conn.prepareStatement(sql1);
			smt.setString(1, deleteInfo.getTeaCode());
			smt.executeUpdate();
			smt.close();

			smt = conn.prepareStatement(sql2);
			smt.setString(1, deleteInfo.getTeaCode());
			smt.executeUpdate();
			smt.close();

		} catch (SQLException e) {
			throw new RuntimeException(e);

		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
				}
			}
		}
	}

	@Override
	public TeacherBasicInfoAdmin get(TeacherBasicInfoAdmin info) {

		String sql = "select teacher.TEA_PHOTO, teacher.TEA_CODE, teacher.TEA_LDAP, members.M_NAME, teacher.TEA_EN_NAME, class_post.POST_NAME, members.M_PHONE, members.M_EMAIL, teacher.Location "
				+ "from teacher, members, class_post, members_class "
				+ "where teacher.TEA_CODE = ? and teacher.TEA_LDAP = members.M_LDAP and members.M_LDAP = members_class.M_LDAP and members_class.M_POST_CODE = class_post.POST_CODE";

		try {
			conn = dataSource.getConnection();
			smt = conn.prepareStatement(sql);
			smt.setString(1, info.getTeaCode());
			rs = smt.executeQuery();
			if (rs.next()) {
				info.setTeaPic(rs.getString("TEA_PHOTO"));
				info.setTeaCode(rs.getString("TEA_CODE"));
				info.setTeaLDAP(rs.getString("TEA_LDAP"));
				info.setTeaName(rs.getString("M_NAME"));
				info.setTeaENName(rs.getString("TEA_EN_NAME"));
				info.setTeaPos(rs.getString("POST_NAME"));
				info.setTeaTel(rs.getString("M_PHONE"));
				info.setTeaEmail(rs.getString("M_EMAIL"));
				info.setTeaLoc(rs.getString("Location"));
			}
			rs.close();
			smt.close();

		} catch (SQLException e) {
			throw new RuntimeException(e);

		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
				}
			}
		}
		return info;
	}

	@Override
	public void changeView(TeacherBasicInfoAdmin changeInfo) {
		String sql = "Update teacher set TEA_ABLE = ? where TEA_LDAP= ?";

		try {
			conn = dataSource.getConnection();
			smt = conn.prepareStatement(sql);
			smt.setString(1, changeInfo.getTeaAble());
			smt.setString(2, changeInfo.getTeaLDAP());
			smt.executeUpdate();
			smt.close();

		} catch (SQLException e) {
			throw new RuntimeException(e);

		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
				}
			}
		}
	}

	@Override
	public void newTeacherBasicInfo(TeacherBasicInfoAdmin newInfo) {
		String sql1 = "insert into teacher(TEA_CODE, TeacherType, TEA_EN_NAME, TEA_PHOTO, "
				+ "TEA_LDAP, TEA_SORT, TEA_ABLE, Location) values(?,?,?,?,?,?,?,?)";
		String sql2 = "insert into members(M_LDAP, M_PASSWORD, M_NAME, M_DEP_CODE, M_PHONE, M_EMAIL) " + "values(?,'test',?,'1',?,?)";
		String sql3 = "insert into members_class(M_LDAP, M_POST_CODE) values(?,?)";

		String teaCode = newTeaCode();
		String teaSort = newTeaSort(newInfo);

		try {
			PreparedStatement smt1 = null;
			PreparedStatement smt2 = null;
			PreparedStatement smt3 = null;
			conn = dataSource.getConnection();

			smt1 = conn.prepareStatement(sql1);
			smt1.setString(1, teaCode);
			smt1.setString(2, newInfo.getTeaType());
			smt1.setString(3, newInfo.getTeaENName());
			smt1.setString(4, newInfo.getTeaPic());
			smt1.setString(5, newInfo.getTeaLDAP());
			smt1.setString(6, teaSort);
			smt1.setString(7, newInfo.getTeaAble());
			smt1.setString(8, newInfo.getTeaLoc());
			smt1.executeUpdate();
			smt1.close();

			smt2 = conn.prepareStatement(sql2);
			smt2.setString(1, newInfo.getTeaLDAP());
			smt2.setString(2, newInfo.getTeaName());
			smt2.setString(3, newInfo.getTeaTel());
			smt2.setString(4, newInfo.getTeaEmail());
			smt2.executeUpdate();
			smt2.close();

			smt3 = conn.prepareStatement(sql3);
			smt3.setString(1, newInfo.getTeaLDAP());
			smt3.setString(2, newInfo.getTeaPos());
			smt3.executeUpdate();
			smt3.close();

		} catch (SQLException e) {
			throw new RuntimeException(e);

		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
				}
			}
		}
	}

	// 自動產生一個新的教師編號
	private String newTeaCode() {
		String teaCode = "";
		String sql = "select max(TEA_CODE) as TEA_CODE from teacher";
		try {
			conn = dataSource.getConnection();
			smt = conn.prepareStatement(sql);
			rs = smt.executeQuery();
			if (rs.next()) {
				int i = rs.getInt("TEA_CODE");
				i++;
				teaCode = Integer.toString(i);
			}
			smt.close();

		} catch (SQLException e) {
			throw new RuntimeException(e);

		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
				}
			}
		}
		return teaCode;
	}

	// 自動產生一個新的教師排序編號，先統一生成該教師類別中的最大值，再通過“更改排序”按鈕進行修改
	private String newTeaSort(TeacherBasicInfoAdmin newInfo) {
		String teaSort = "";
		String sql = "select max(TEA_SORT) as TEA_SORT from teacher, members_class where Teachertype=?";

		try {
			conn = dataSource.getConnection();
			smt = conn.prepareStatement(sql);
			smt.setString(1, newInfo.getTeaType());
			rs = smt.executeQuery();
			if (rs.next()) {
				int i = rs.getInt("TEA_SORT");
				i++;
				teaSort = Integer.toString(i);
			}
			smt.close();

		} catch (SQLException e) {
			throw new RuntimeException(e);

		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
				}
			}
		}

		return teaSort;
	}
}
