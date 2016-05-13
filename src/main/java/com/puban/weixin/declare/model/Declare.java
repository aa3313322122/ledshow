package com.puban.weixin.declare.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

import com.puban.framework.core.model.BaseModel;
import com.puban.weixin.channel.model.Channel;
import com.puban.weixin.user.model.User;

@Entity
@Table(name = "t_declare")
public class Declare extends BaseModel
{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer fdId;

	/** 客户名称 **/
	@Column(name = "fd_customer_name")
	private String fdCustomerName;

	/** 身份证号码 **/
	@Column(name = "fd_identity_card")
	private String fdIdentityCard;

	/** 身份证照片 **/
	@Column(name = "fd_identity_card_path_p")
	private String fdIdentityCardPathP;

	/** 房产证照片1 **/
	@Column(name = "fd_property_card_path")
	private String fdPropertyCardPath;

	/** 房产证照片2 **/
	@Column(name = "fd_property_card_path_s")
	private String fdPropertyCardPathS;

	/** 房产证照片3 **/
	@Column(name = "fd_property_card_path_t")
	private String fdPropertyCardPathT;
	
	@Type(type="text")
	@Column(name = "fd_other_file_path", length=16777216)
	private String fdOtherFilePath;

	/** 借款人电话 **/
	@Column(name = "fd_borrower_phone")
	private String fdBorrowerPhone;

	/** 借款金额 **/
	@Column(name = "fd_borrow_amount")
	private Double fdBorrowAmount;

	/** 借款期限 **/
	@Column(name = "fd_borrow_term")
	private String fdBorrowTerm;

	/** 房产地址 **/
	@Column(name = "fd_mortgage_address")
	private String fdMortgageAddress;

	/** 房产面积 **/
	@Column(name = "fd_mortgage_acreage")
	private String fdMortgageAcreage;

	/** 房产总金额 **/
	@Column(name = "fd_mortgage_price")
	private Double fdMortgagePrice;

	/** 申报人手机 **/
	@Column(name = "fd_declarer_phone")
	private String fdDeclarerPhone;

	/** 申报人最新手机验证码 **/
	@Column(name = "fd_validate_code")
	private String fdValidateCode;

	/** 申报单据状态 1为未受理 2为已受理 **/
	@Column(name = "fd_status")
	private Integer fdStatus;

	/** 申报创建时间 **/
	@Column(name = "fd_create_time")
	private String fdCreateTime;

	/** 反馈给微信客户端的的可贷金额 **/
	@Column(name = "fd_amount_loanable")
	private Double fdAmountLoanable;

	/** 预约联系人 **/
	@Column(name = "fd_appointment_contact")
	private String fdAppointmentContact;

	/** 预约联系人信息 **/
	@Column(name = "fd_appointment_information")
	private String fdAppointmentInformation;

	/** 单据所属用户 **/
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	/** 单据所属渠道 **/
	@ManyToOne
	@JoinColumn(name = "channel_id")
	private Channel channel;

	/** 普贷通申报单id **/
	@Column(name = "apply_id")
	private String applyId;

	/** 业务类型 **/
	@Column(name = "declare_type")
	private Integer declareType;

	/** 反填金额 **/
	@Column(name = "fd_filling_amount")
	private Double fdFillingAmount;

	public Integer getFdId()
	{
		return fdId;
	}

	public void setFdId(Integer fdId)
	{
		this.fdId = fdId;
	}

	public String getFdCustomerName()
	{
		return fdCustomerName;
	}

	public void setFdCustomerName(String fdCustomerName)
	{
		this.fdCustomerName = fdCustomerName;
	}

	public String getFdIdentityCard()
	{
		return fdIdentityCard;
	}

	public void setFdIdentityCard(String fdIdentityCard)
	{
		this.fdIdentityCard = fdIdentityCard;
	}

	public String getFdIdentityCardPathP()
	{
		return fdIdentityCardPathP;
	}

	public void setFdIdentityCardPathP(String fdIdentityCardPathP)
	{
		this.fdIdentityCardPathP = fdIdentityCardPathP;
	}

	public String getFdPropertyCardPath()
	{
		return fdPropertyCardPath;
	}

	public void setFdPropertyCardPath(String fdPropertyCardPath)
	{
		this.fdPropertyCardPath = fdPropertyCardPath;
	}

	public String getFdPropertyCardPathS()
	{
		return fdPropertyCardPathS;
	}

	public void setFdPropertyCardPathS(String fdPropertyCardPathS)
	{
		this.fdPropertyCardPathS = fdPropertyCardPathS;
	}

	public String getFdPropertyCardPathT()
	{
		return fdPropertyCardPathT;
	}

	public void setFdPropertyCardPathT(String fdPropertyCardPathT)
	{
		this.fdPropertyCardPathT = fdPropertyCardPathT;
	}

	public String getFdBorrowerPhone()
	{
		return fdBorrowerPhone;
	}

	public void setFdBorrowerPhone(String fdBorrowerPhone)
	{
		this.fdBorrowerPhone = fdBorrowerPhone;
	}

	public Double getFdBorrowAmount()
	{
		return fdBorrowAmount;
	}

	public void setFdBorrowAmount(Double fdBorrowAmount)
	{
		this.fdBorrowAmount = fdBorrowAmount;
	}

	public String getFdBorrowTerm()
	{
		return fdBorrowTerm;
	}

	public void setFdBorrowTerm(String fdBorrowTerm)
	{
		this.fdBorrowTerm = fdBorrowTerm;
	}

	public String getFdMortgageAddress()
	{
		return fdMortgageAddress;
	}

	public void setFdMortgageAddress(String fdMortgageAddress)
	{
		this.fdMortgageAddress = fdMortgageAddress;
	}

	public String getFdMortgageAcreage()
	{
		return fdMortgageAcreage;
	}

	public void setFdMortgageAcreage(String fdMortgageAcreage)
	{
		this.fdMortgageAcreage = fdMortgageAcreage;
	}

	public Double getFdMortgagePrice()
	{
		return fdMortgagePrice;
	}

	public void setFdMortgagePrice(Double fdMortgagePrice)
	{
		this.fdMortgagePrice = fdMortgagePrice;
	}

	public String getFdDeclarerPhone()
	{
		return fdDeclarerPhone;
	}

	public void setFdDeclarerPhone(String fdDeclarerPhone)
	{
		this.fdDeclarerPhone = fdDeclarerPhone;
	}

	public String getFdValidateCode()
	{
		return fdValidateCode;
	}

	public void setFdValidateCode(String fdValidateCode)
	{
		this.fdValidateCode = fdValidateCode;
	}

	public Integer getFdStatus()
	{
		return fdStatus;
	}

	public void setFdStatus(Integer fdStatus)
	{
		this.fdStatus = fdStatus;
	}

	public String getFdCreateTime()
	{
		return fdCreateTime;
	}

	public void setFdCreateTime(String fdCreateTime)
	{
		this.fdCreateTime = fdCreateTime;
	}

	public Double getFdAmountLoanable()
	{
		return fdAmountLoanable;
	}

	public void setFdAmountLoanable(Double fdAmountLoanable)
	{
		this.fdAmountLoanable = fdAmountLoanable;
	}

	public String getFdAppointmentContact()
	{
		return fdAppointmentContact;
	}

	public void setFdAppointmentContact(String fdAppointmentContact)
	{
		this.fdAppointmentContact = fdAppointmentContact;
	}

	public String getFdAppointmentInformation()
	{
		return fdAppointmentInformation;
	}

	public void setFdAppointmentInformation(String fdAppointmentInformation)
	{
		this.fdAppointmentInformation = fdAppointmentInformation;
	}

	public User getUser()
	{
		return user;
	}

	public void setUser(User user)
	{
		this.user = user;
	}

	public Channel getChannel()
	{
		return channel;
	}

	public void setChannel(Channel channel)
	{
		this.channel = channel;
	}

	public String getApplyId()
	{
		return applyId;
	}

	public void setApplyId(String applyId)
	{
		this.applyId = applyId;
	}

	public Integer getDeclareType()
	{
		return declareType;
	}

	public void setDeclareType(Integer declareType)
	{
		this.declareType = declareType;
	}

	public Double getFdFillingAmount() {
		return fdFillingAmount;
	}

	public void setFdFillingAmount(Double fdFillingAmount) {
		this.fdFillingAmount = fdFillingAmount;
	}

	public String getFdOtherFilePath()
	{
		return fdOtherFilePath;
	}

	public void setFdOtherFilePath(String fdOtherFilePath)
	{
		this.fdOtherFilePath = fdOtherFilePath;
	}

	@Override
	public String toString()
	{
		return "Declare [fdId=" + fdId + ", fdCustomerName=" + fdCustomerName + ", fdIdentityCard=" + fdIdentityCard + ", fdIdentityCardPathP=" + fdIdentityCardPathP + ", fdPropertyCardPath=" + fdPropertyCardPath + ", fdPropertyCardPathS=" + fdPropertyCardPathS + ", fdPropertyCardPathT=" + fdPropertyCardPathT + ", fdOtherFilePath=" + fdOtherFilePath + ", fdBorrowerPhone=" + fdBorrowerPhone + ", fdBorrowAmount=" + fdBorrowAmount + ", fdBorrowTerm=" + fdBorrowTerm + ", fdMortgageAddress=" + fdMortgageAddress + ", fdMortgageAcreage=" + fdMortgageAcreage + ", fdMortgagePrice=" + fdMortgagePrice + ", fdDeclarerPhone=" + fdDeclarerPhone + ", fdValidateCode=" + fdValidateCode + ", fdStatus=" + fdStatus + ", fdCreateTime=" + fdCreateTime + ", fdAmountLoanable=" + fdAmountLoanable + ", fdAppointmentContact=" + fdAppointmentContact + ", fdAppointmentInformation=" + fdAppointmentInformation + ", user=" + user + ", channel=" + channel + ", applyId=" + applyId + ", declareType=" + declareType + ", fdFillingAmount=" + fdFillingAmount + "]";
	}
}
