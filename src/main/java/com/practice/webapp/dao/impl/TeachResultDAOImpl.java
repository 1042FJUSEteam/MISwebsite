package com.practice.webapp.dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import com.practice.webapp.dao.Teach_ResultDAO;
import com.practice.webapp.entity.teachresult.*;

public class TeachResultDAOImpl implements Teach_ResultDAO {
	private DataSource dataSource;
	private Connection conn = null;
	private ResultSet rs = null;
	private PreparedStatement smt = null;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	public List<Sym> getSymList() {
		List<Sym> symList = new ArrayList<Sym>();

		String sql = "SELECT * FROM symposium";

		try {
			conn = dataSource.getConnection();
			smt = conn.prepareStatement(sql);
			rs = smt.executeQuery();

			while (rs.next()) {
				Sym sym = new Sym();
				sym.setSym_code(rs.getInt("SYM_CODE"));
				sym.setSym_name(rs.getString("SYM_NAME"));
				sym.setSym_time(rs.getDate("SYM_TIME"));
				sym.setSym_url(rs.getString("SYM_URL"));

				symList.add(sym);
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
		return symList;
	}

	public List<Graduation> getGraduationList() {
		List<Graduation> graduationList = new ArrayList<Graduation>();

		String sql = "SELECT * FROM graduation";

		try {
			conn = dataSource.getConnection();
			smt = conn.prepareStatement(sql);
			rs = smt.executeQuery();

			while (rs.next()) {
				Graduation graduation = new Graduation();
				graduation.setGra_code(rs.getString("GRA_CODE"));
				graduation.setGra_year(rs.getString("GRA_YEAR"));
				graduation.setGra_title(rs.getString("GRA_TITLE"));
				graduation.setGra_teacher(rs.getString("GRA_TEACHER"));
				graduation.setGra_student(rs.getString("GRA_STUDENT"));
				graduation.setGra_path(rs.getString("GRA_PATH"));
				graduation.setSecondteacher(rs.getString("SecondTeacher"));

				graduationList.add(graduation);
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
		return graduationList;
	}

	public List<Tea_stu_paper> getTea_stu_paperList() {
		List<Tea_stu_paper> tea_stu_paperList = new ArrayList<Tea_stu_paper>();

		String sql = "SELECT * FROM tea_stu_paper, teacher, members "
				+ "WHERE tea_stu_paper.TEA_CODE = teacher.TEA_CODE AND teacher.TEA_LDAP = members.M_LDAP";

		try {
			conn = dataSource.getConnection();
			smt = conn.prepareStatement(sql);
			rs = smt.executeQuery();

			while (rs.next()) {
				Tea_stu_paper tea_stu_paper = new Tea_stu_paper();
				tea_stu_paper.setPaperid(rs.getInt("PaperID"));
				tea_stu_paper.setTea_code(rs.getInt("TEA_CODE"));
				tea_stu_paper.setTea_stu_year(rs.getInt("TEA_STU_YEAR"));
				tea_stu_paper.setTea_stu_name(rs.getString("TEA_STU_NAME"));
				tea_stu_paper.setTea_stu_paper_name(rs.getString("TEA_STU_PAPER_NAME"));
				tea_stu_paper.setTea_name(rs.getString("members.M_NAME"));

				tea_stu_paperList.add(tea_stu_paper);
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
		return tea_stu_paperList;
	}

	public List<Award> getAwardList() {
		List<Award> awardList = new ArrayList<Award>();

		String sql = "SELECT * FROM article WHERE ArticleID>0";

		try {
			conn = dataSource.getConnection();
			smt = conn.prepareStatement(sql);
			rs = smt.executeQuery();

			while (rs.next()) {
				Award award = new Award();
				award.setArticleid(rs.getInt("ArticleID"));
				award.setArticlecontent(rs.getString("ArticleContent"));
				award.setArticleenglishcontent(rs.getString("ArticleEnglishContent"));
				awardList.add(award);
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
		return awardList;
	}

	public void insertSym(Sym sym) {
		// TODO Auto-generated method stub
		String sql = "INSERT symposium(SYM_CODE,SYM_NAME,SYM_TIME,SYM_URL) " + "VALUES(?,?,NOW(),?)";

		// String sql2 = "SELECT MAX(SYM_CODE) FROM symposium";
		// conn = dataSource.getConnection();
		// smt = conn.prepareStatement(sql2);
		// rs = smt.executeQuery();
		// int max = rs.getInt("SYM_CODE");

		try {
			conn = dataSource.getConnection();
			smt = conn.prepareStatement(sql);
			smt.setInt(1, sym.getSym_code());
			smt.setString(2, sym.getSym_name());
			// smt.setDate(3, (Date) sym.getSym_time());
			smt.setString(3, sym.getSym_url());
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
	public void insertGraduation(Graduation graduation) {
		// TODO Auto-generated method stub
		String sql = "INSERT graduation(GRA_CODE,GRA_YEAR,GRA_TITLE,GRA_TEACHER,GRA_STUDENT,GRA_PATH,SecondTeacher) "
				+ "VALUES(?,?,?,?,?,?,?)";

		try {
			conn = dataSource.getConnection();
			smt = conn.prepareStatement(sql);
			smt.setString(1, graduation.getGra_code());
			smt.setString(2, graduation.getGra_year());
			smt.setString(3, graduation.getGra_title());
			smt.setString(4, graduation.getGra_teacher());
			smt.setString(5, graduation.getGra_student());
			smt.setString(6, graduation.getGra_path());
			smt.setString(7, graduation.getSecondteacher());
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
	public void insertTea_stu_paper(Tea_stu_paper tea_stu_paper) {
		// TODO Auto-generated method stub
		String sql = "INSERT tea_stu_paper(PaperID,TEA_CODE,TEA_STU_YEAR,TEA_STU_NAME,TEA_STU_PAPER_NAME) "
				+ "VALUES(?,?,?,?,?)";

		try {
			conn = dataSource.getConnection();
			smt = conn.prepareStatement(sql);
			smt.setInt(1, tea_stu_paper.getPaperid());
			smt.setInt(2, tea_stu_paper.getTea_code());
			smt.setInt(3, tea_stu_paper.getTea_stu_year());
			smt.setString(4, tea_stu_paper.getTea_stu_name());
			smt.setString(5, tea_stu_paper.getTea_stu_paper_name());
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
	public void insertAward(Award award) {
		// TODO Auto-generated method stub
		String sql = "INSERT into article(ArticleID,ArticleContent,ArticleEnglishContent) VALUES(?,?,?)";

		try {
			conn = dataSource.getConnection();
			smt = conn.prepareStatement(sql);
			smt.setInt(1, award.getArticleid());
			smt.setString(2, award.getArticlecontent());
			smt.setString(3, award.getArticleenglishcontent());

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
	public void deleteSym(Sym sym) {
		String sql = "DELETE FROM symposium WHERE SYM_CODE = ?";
		try {
			conn = dataSource.getConnection();
			smt = conn.prepareStatement(sql);
			smt.setInt(1, sym.getSym_code());
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
	public void deleteGraduation(Graduation graduation) {
		// TODO Auto-generated method stub
		String sql = "DELETE FROM graduation WHERE GRA_CODE = ?";
		try {
			conn = dataSource.getConnection();
			smt = conn.prepareStatement(sql);
			smt.setString(1, graduation.getGra_code());
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
	public void deleteTea_stu_paper(Tea_stu_paper tea_stu_paper) {
		// TODO Auto-generated method stub
		String sql = "DELETE FROM tea_stu_paper WHERE PaperID = ?";
		try {
			conn = dataSource.getConnection();
			smt = conn.prepareStatement(sql);
			smt.setInt(1, tea_stu_paper.getPaperid());
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
	public void deleteAward(Award award) {
		// TODO Auto-generated method stub
		String sql = "DELETE FROM article WHERE ArticleID = ?";
		try {
			conn = dataSource.getConnection();
			smt = conn.prepareStatement(sql);
			smt.setInt(1, award.getArticleid());
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
	public void updateSym(Sym sym) {
		// TODO Auto-generated method stub
		String sql = "UPDATE symposium SET SYM_CODE=?, SYM_NAME=?, SYM_TIME=now(), SYM_URL=? " + "WHERE SYM_CODE = ?";
		try {
			conn = dataSource.getConnection();
			smt = conn.prepareStatement(sql);
			smt.setInt(1, sym.getSym_code());
			smt.setString(2, sym.getSym_name());
			smt.setString(3, sym.getSym_url());
			smt.setInt(4, (int) sym.getSym_code());
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
	public void updateGraduation(Graduation graduation) {
		// TODO Auto-generated method stub
		String sql = "UPDATE graduation SET GRA_CODE=?,GRA_YEAR=?,GRA_TITLE=?,GRA_TEACHER=?,GRA_STUDENT=?,GRA_PATH=?,SecondTeacher=? "
				+ "WHERE GRA_CODE = ?";
		try {
			conn = dataSource.getConnection();
			smt = conn.prepareStatement(sql);
			smt.setString(1, graduation.getGra_code());
			smt.setString(2, graduation.getGra_year());
			smt.setString(3, graduation.getGra_title());
			smt.setString(4, graduation.getGra_teacher());
			smt.setString(5, graduation.getGra_student());
			smt.setString(6, graduation.getGra_path());
			smt.setString(7, graduation.getSecondteacher());
			smt.setString(8, graduation.getGra_code());
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
	public void updateTea_stu_paper(Tea_stu_paper tea_stu_paper) {
		// TODO Auto-generated method stub
		String sql = "UPDATE tea_stu_paper SET PaperID = ?,TEA_CODE = ?,TEA_STU_YEAR = ?,TEA_STU_NAME = ?,TEA_STU_PAPER_NAME =?"
				+ "WHERE PaperID = ?";

		try {
			conn = dataSource.getConnection();
			smt = conn.prepareStatement(sql);
			smt.setInt(1, tea_stu_paper.getPaperid());
			smt.setInt(2, tea_stu_paper.getTea_code());
			smt.setInt(3, tea_stu_paper.getTea_stu_year());
			smt.setString(4, tea_stu_paper.getTea_stu_name());
			smt.setString(5, tea_stu_paper.getTea_stu_paper_name());
			smt.setInt(6, tea_stu_paper.getPaperid());
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
	public void updateAward(Award award) {
		// TODO Auto-generated method stub
		String sql = "UPDATE article SET ArticleID=?,ArticleContent=?,ArticleEnglishContent=?  WHERE ArticleID = ?";

		try {
			conn = dataSource.getConnection();
			smt = conn.prepareStatement(sql);
			smt.setInt(1, award.getArticleid());
			smt.setString(2, award.getArticlecontent());
			smt.setString(3, award.getArticleenglishcontent());
			smt.setInt(4, award.getArticleid());
			
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
	public Sym get(Sym sym) {
		String sql = "SELECT * FROM symposium WHERE SYM_CODE = ?";
		try {
			conn = dataSource.getConnection();
			smt = conn.prepareStatement(sql);
			smt.setInt(1, sym.getSym_code());
			rs = smt.executeQuery();
			if (rs.next()) {
				sym.setSym_code(rs.getInt("SYM_CODE"));
				sym.setSym_name(rs.getString("SYM_NAME"));
				sym.setSym_url(rs.getString("SYM_URL"));
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

		return sym;
	}

	@Override
	public Graduation get(Graduation graduation) {
		String sql = "SELECT * FROM graduation WHERE GRA_CODE = ?";
		try {
			conn = dataSource.getConnection();
			smt = conn.prepareStatement(sql);
			smt.setString(1, graduation.getGra_code());
			rs = smt.executeQuery();
			if (rs.next()) {
				graduation.setGra_code(rs.getString("GRA_CODE"));
				graduation.setGra_year(rs.getString("GRA_YEAR"));
				graduation.setGra_title(rs.getString("GRA_TITLE"));
				graduation.setGra_teacher(rs.getString("GRA_TEACHER"));
				graduation.setGra_student(rs.getString("GRA_STUDENT"));
				graduation.setGra_path(rs.getString("GRA_PATH"));
				graduation.setSecondteacher(rs.getString("SecondTeacher"));
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

		return graduation;
	}

	@Override
	public Tea_stu_paper get(Tea_stu_paper tea_stu_paper) {
		// TODO Auto-generated method stub
		String sql = "SELECT * FROM tea_stu_paper WHERE PaperID = ?";
		try {
			conn = dataSource.getConnection();
			smt = conn.prepareStatement(sql);
			smt.setInt(1, tea_stu_paper.getPaperid());
			rs = smt.executeQuery();
			if (rs.next()) {
				tea_stu_paper.setPaperid(rs.getInt("PaperID"));
				tea_stu_paper.setTea_code(rs.getInt("TEA_CODE"));
				tea_stu_paper.setTea_stu_year(rs.getInt("TEA_STU_YEAR"));
				tea_stu_paper.setTea_stu_name(rs.getString("TEA_STU_NAME"));
				tea_stu_paper.setTea_stu_paper_name(rs.getString("TEA_STU_PAPER_NAME"));
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
		return tea_stu_paper;
	}

	@Override
	public Award get(Award award) {
		// TODO Auto-generated method stub
		String sql = "SELECT * FROM article WHERE ArticleID = ?";
		try {
			conn = dataSource.getConnection();
			smt = conn.prepareStatement(sql);
			smt.setInt(1, award.getArticleid());
			rs = smt.executeQuery();
			if (rs.next()) {
				award.setArticleid(rs.getInt("ArticleID"));
				award.setArticlecontent(rs.getString("ArticleContent"));
				award.setArticleenglishcontent(rs.getString("ArticleEnglishContent"));
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
		return award;
	}

	@Override
	public Sym getSym(int sym_code) {
		Sym out = new Sym();

		String sql = "SELECT * FROM symposium where SYM_CODE = ?";

		try {
			conn = dataSource.getConnection();
			smt = conn.prepareStatement(sql);
			smt.setInt(1, sym_code);
			rs = smt.executeQuery();
			if (rs.next()) {
				out.setSym_code(rs.getInt("SYM_CODE"));
				out.setSym_name(rs.getString("SYM_NAME"));
				// out.setSym_time(rs.getDate("SYM_TIME"));
				out.setSym_url(rs.getString("SYM_URL"));
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
		return out;
	}

	@Override
	public Graduation getGraduation(String gra_code) {
		Graduation out = new Graduation();
		String sql = "SELECT * FROM graduation where GRA_CODE = ?";

		try {
			conn = dataSource.getConnection();
			smt = conn.prepareStatement(sql);
			smt.setString(1, gra_code);
			rs = smt.executeQuery();
			if (rs.next()) {
				out.setGra_code(rs.getString("GRA_CODE"));
				out.setGra_year(rs.getString("GRA_YEAR"));
				out.setGra_title(rs.getString("GRA_TITLE"));
				out.setGra_teacher(rs.getString("GRA_TEACHER"));
				out.setGra_student(rs.getString("GRA_STUDENT"));
				out.setGra_path(rs.getString("GRA_PATH"));
				out.setSecondteacher(rs.getString("SecondTeacher"));
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
		return out;
	}

	@Override
	public Tea_stu_paper getTea_stu_paper(int paperid) {
		Tea_stu_paper out = new Tea_stu_paper();
		String sql = "SELECT * FROM tea_stu_paper where PaperID = ?";

		try {
			conn = dataSource.getConnection();
			smt = conn.prepareStatement(sql);
			smt.setInt(1, paperid);
			rs = smt.executeQuery();
			if (rs.next()) {
				out.setPaperid(rs.getInt("PaperID"));
				out.setTea_code(rs.getInt("TEA_CODE"));
				out.setTea_stu_year(rs.getInt("TEA_STU_YEAR"));
				out.setTea_stu_name(rs.getString("TEA_STU_NAME"));
				out.setTea_stu_paper_name(rs.getString("TEA_STU_PAPER_NAME"));
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
		return out;
	}

	@Override
	public Award getAward(int articleid) {
		Award out = new Award();
		String sql = "SELECT * FROM article where ArticleID = ?";

		try {
			conn = dataSource.getConnection();
			smt = conn.prepareStatement(sql);
			smt.setInt(1, articleid);
			rs = smt.executeQuery();
			if (rs.next()) {
				out.setArticleid(rs.getInt("ArticleID"));
				out.setArticlecontent(rs.getString("ArticleContent"));
				out.setArticleenglishcontent(rs.getString("ArticleEnglishContent"));
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
		return out;
	}

}
