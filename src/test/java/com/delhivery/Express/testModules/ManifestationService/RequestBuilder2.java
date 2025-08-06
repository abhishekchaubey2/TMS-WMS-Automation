package com.delhivery.Express.testModules.ManifestationService;

import static com.delhivery.core.utils.Utilities.getUniqueString;

import java.util.*;

import com.delhivery.Express.pojo.ClientUpdateNew.Request.*;
import com.delhivery.Express.pojo.ManifestMpsMasterChild.Request.CreateMPS;
import com.delhivery.Express.pojo.ManifestMpsMasterChild.Request.ShipmentMps;
import com.delhivery.Express.pojo.Meesho1.Request.*;
import com.delhivery.Express.pojo.Meesho3.Request.*;
import com.delhivery.Express.pojo.MultiItem.request.ItemList;
import com.delhivery.Express.pojo.MultiItem.request.MultiItemShipment;
import com.delhivery.Express.pojo.MultiItem.request.PickupLocation;
import com.delhivery.Express.pojo.MultiItem.request.multiItemRequest;
import com.delhivery.Express.pojo.NewManifestMadatoryKeys.request.MandatoryKeys;
import com.delhivery.Express.pojo.NewManifestMadatoryKeys.request.Shipments;
import com.delhivery.Express.pojo.NewManifestService.request.Manifest;
import com.delhivery.Express.pojo.NewManifestService.request.Shipment;
import com.delhivery.Express.pojo.NewManifestService2.request.Manifest2;
import com.delhivery.Express.pojo.NewManifestService2.request.Shipment2;
import com.delhivery.Express.pojo.NewManifestService3.request.Manifest3;
import com.delhivery.Express.pojo.NewManifestService3.request.Shipment3;
import com.delhivery.core.utils.ConfigLoader;
import com.delhivery.core.utils.YamlReader;

public class RequestBuilder2 {
	protected static Map<String, Object> clientDetails = YamlReader
			.getYamlValues("Client_Details.client_" + ConfigLoader.getInstance().getManifestRegressionClient());
	//getManifestRegressionClient());
	private static String pdt = "B2C";
	private static String paymentMode = "prepaid";
	private static String pin = "400059";

	public static Manifest NewManifestApiReqGen(HashMap<String, Object> data) {
		List<Shipment> listShipments = new ArrayList<>();
		listShipments.add(NewShipments(data));
		Manifest body = Manifest.builder()
				.shipments(listShipments)
				.build();
		return body;
	}

	public static Manifest2 NewManifestApiReqGen2(HashMap<String, Object> data) {
		List<Shipment2> listShipments = new ArrayList<>();
		listShipments.add(NewShipments2(data));
		Manifest2 body = Manifest2.builder()
				.shipments(listShipments)
				.build();
		return body;
	}

	public static Manifest3 NewManifestApiReqGen3(HashMap<String, Object> data) {
		List<Shipment3> listShipments = new ArrayList<>();
		listShipments.add(NewShipments3(data));
		Manifest3 body = Manifest3.builder()
				.shipments(listShipments)
				.build();
		return body;
	}

	public static Shipment NewShipments(HashMap<String, Object> data) {
		Shipment payload = Shipment.builder()
				.name("test")
				.client(clientDetails.get("name").toString())
				.waybill("")
				.order(getUniqueString())
				.productsDesc("test")
				.orderDate("")
				.paymentMode(paymentMode)
				.codAmount(10)
				.productType(pdt)
				.mpsAmount("")
				.packageCount("")
				.totalAmount("")
				.add("test add")
				.city("")
				.state("")
				.country("")
				.phone("9617113651")
				.pin(pin)
				.returnAdd("")
				.returnCity("")
				.returnCountry("")
				.returnName("")
				.returnPhone("")
				.returnPin("")
				.returnState("")
				.supplier("")
				.billableWeight("")
				.shippingMethod("")
				.taxValue("true")
				.taxationType("")
				.fragileShipment("")
				.documentNumber("")
				.ewbn("")
				.source("")
				.shippingMode("")
				.secondaryAdd("")
				.secondaryCity("")
				.secondaryState("")
				.weightVerification("")
				.essentialGood("")
				.email("")
				.weight("")
				.invoiceUrl("")
				.quantity("")
				.sellerName("")
				.sellerAdd("")
				.sellerCity("")
				.sellerState("")
				.sellerCst("")
				.sellerTin("")
				.sellerInv("")
				.sellerInvDate("")
				.shipmentLength("")
				.shipmentWidth("")
				.shipmentHeight("")
				.consigneeTin("")
				.commodityValue("")
				.categoryOfGoods("")
				.dangerousGoods("")
				.shipmentType("")
				.sellerGstTin("")
				.clientGstTin("")
				.consigneeGstTin("")
				.hsnCode("")
				.invoiceReference("")
				.odDistance("")
				.billingName("")
				.countryCode("")
				.clearanceMode("")
				.fmAddress("")
				.fmPhone("")
				.fmPin("")
				.transportSpeed("")
				.masterId("")
				.pickupStartTime("")
				.pickupEndTime("")
				.pickupSlotCode("")
				.dropEndTime("")
				.dropSlotCode("")
				.dropStartTime("")
				.serviceType("")
				.volumetric("")
				.build();


		for (Map.Entry<String, Object> set : data.entrySet()) {
			String k = set.getKey();
			Object v = set.getValue();

			if (k.equalsIgnoreCase("name")) {
				payload.setName(v);
			} else if (k.equalsIgnoreCase("waybill")) {
				payload.setWaybill(v);
			} else if (k.equalsIgnoreCase("order")) {
				payload.setOrder(v);
			} else if (k.equalsIgnoreCase("productsDesc")) {
				payload.setProductsDesc(v);
			} else if (k.equalsIgnoreCase("orderDate")) {
				payload.setOrderDate(v);
			} else if (k.equalsIgnoreCase("paymentMode")) {
				payload.setPaymentMode(v);
			} else if (k.equalsIgnoreCase("productType")) {
				payload.setProductType(v);
			} else if (k.equalsIgnoreCase("codAmount")) {
				payload.setCodAmount(v);
			} else if (k.equalsIgnoreCase("totalAmount")) {
				payload.setTotalAmount(v);
			} else if (k.equalsIgnoreCase("mpsAmount")) {
				payload.setMpsAmount(v);
			} else if (k.equalsIgnoreCase("add")) {
				payload.setAdd(v);
			} else if (k.equalsIgnoreCase("city")) {
				payload.setCity(v);
			} else if (k.equalsIgnoreCase("state")) {
				payload.setState(v);
			} else if (k.equalsIgnoreCase("country")) {
				payload.setCountry(v);
			} else if (k.equalsIgnoreCase("taxationType")) {
				payload.setTaxationType(v);
			} else if (k.equalsIgnoreCase("phone")) {
				payload.setPhone(v);
			} else if (k.equalsIgnoreCase("pin")) {
				payload.setPin(v);
			} else if (k.equalsIgnoreCase("supplier")) {
				payload.setSupplier(v);
			} else if (k.equalsIgnoreCase("billableWeight")) {
				payload.setBillableWeight(v);
			} else if (k.equalsIgnoreCase("returnAdd")) {
				payload.setReturnAdd(v);
			} else if (k.equalsIgnoreCase("returnState")) {
				payload.setReturnState(v);
			} else if (k.equalsIgnoreCase("returnCity")) {
				payload.setReturnCity(v);
			} else if (k.equalsIgnoreCase("returnCountry")) {
				payload.setReturnCountry(v);
			} else if (k.equalsIgnoreCase("returnName")) {
				payload.setReturnName(v);
			} else if (k.equalsIgnoreCase("returnPin")) {
				payload.setReturnPin(v);
			} else if (k.equalsIgnoreCase("fragileShipment")) {
				payload.setFragileShipment(v);
			} else if (k.equalsIgnoreCase("documentNumber")) {
				payload.setDocumentNumber(v);
			} else if (k.equalsIgnoreCase("ewbn")) {
				payload.setEwbn(v);
			} else if (k.equalsIgnoreCase("source")) {
				payload.setSource(v);
			} else if (k.equalsIgnoreCase("returnPhone")) {
				payload.setReturnPhone(v);
			} else if (k.equalsIgnoreCase("shippingMode")) {
				payload.setShippingMode(v);
			} else if (k.equalsIgnoreCase("shippingMethod")) {
				payload.setShippingMethod(v);
			} else if (k.equalsIgnoreCase("secondaryAdd")) {
				payload.setSecondaryAdd(v);
			} else if (k.equalsIgnoreCase("secondaryCity")) {
				payload.setSecondaryCity(v);
			} else if (k.equalsIgnoreCase("secondaryState")) {
				payload.setSecondaryState(v);
			} else if (k.equalsIgnoreCase("secondaryPin")) {
				payload.setSecondaryPin(v);
			} else if (k.equalsIgnoreCase("secondaryCountry")) {
				payload.setSecondaryCountry(v);
			} else if (k.equalsIgnoreCase("packageCount")) {
				payload.setPackageCount(v);
			} else if (k.equalsIgnoreCase("weightVerification")) {
				payload.setWeightVerification(v);
			} else if (k.equalsIgnoreCase("essentialGood")) {
				payload.setEssentialGood(v);
			} else if (k.equalsIgnoreCase("taxValue")) {
				payload.setTaxValue(v);
			} else if (k.equalsIgnoreCase("email")) {
				payload.setEmail(v);
			} else if (k.equalsIgnoreCase("weight")) {
				payload.setWeight(v);
			} else if (k.equalsIgnoreCase("invoiceUrl")) {
				payload.setInvoiceUrl(v);
			} else if (k.equalsIgnoreCase("quantity")) {
				payload.setQuantity(v);
			} else if (k.equalsIgnoreCase("sellerName")) {
				payload.setSellerName(v);
			} else if (k.equalsIgnoreCase("sellerAdd")) {
				payload.setSellerAdd(v);
			} else if (k.equalsIgnoreCase("sellerCity")) {
				payload.setSellerCity(v);
			} else if (k.equalsIgnoreCase("sellerState")) {
				payload.setSellerState(v);
			} else if (k.equalsIgnoreCase("sellerCst")) {
				payload.setSellerCst(v);
			} else if (k.equalsIgnoreCase("sellerTin")) {
				payload.setSellerTin(v);
			} else if (k.equalsIgnoreCase("sellerInv")) {
				payload.setSellerInv(v);
			} else if (k.equalsIgnoreCase("sellerInvDate")) {
				payload.setSellerInvDate(v);
			} else if (k.equalsIgnoreCase("shipmentLength")) {
				payload.setShipmentLength(v);
			} else if (k.equalsIgnoreCase("shipmentWidth")) {
				payload.setShipmentWidth(v);
			} else if (k.equalsIgnoreCase("shipmentHeight")) {
				payload.setShipmentHeight(v);
			} else if (k.equalsIgnoreCase("consigneeTin")) {
				payload.setConsigneeTin(v);
			} else if (k.equalsIgnoreCase("commodityValue")) {
				payload.setCommodityValue(v);
			} else if (k.equalsIgnoreCase("categoryOfGoods")) {
				payload.setCategoryOfGoods(v);
			} else if (k.equalsIgnoreCase("dangerousGoods")) {
				payload.setDangerousGoods(v);
			} else if (k.equalsIgnoreCase("shipmentType")) {
				payload.setShipmentType(v);
			} else if (k.equalsIgnoreCase("sellerGstTin")) {
				payload.setSellerGstTin(v);
			} else if (k.equalsIgnoreCase("clientGstTin")) {
				payload.setClientGstTin(v);
			} else if (k.equalsIgnoreCase("consigneeGstTin")) {
				payload.setConsigneeGstTin(v);
			} else if (k.equalsIgnoreCase("hsnCode")) {
				payload.setHsnCode(v);
			} else if (k.equalsIgnoreCase("invoiceReference")) {
				payload.setInvoiceReference(v);
			} else if (k.equalsIgnoreCase("odDistance")) {
				payload.setOdDistance(v);
			} else if (k.equalsIgnoreCase("billingName")) {
				payload.setBillingName(v);
			} else if (k.equalsIgnoreCase("countryCode")) {
				payload.setCountryCode(v);
			} else if (k.equalsIgnoreCase("clearanceMode")) {
				payload.setClearanceMode(v);
			} else if (k.equalsIgnoreCase("fmAddress")) {
				payload.setFmAddress(v);
			} else if (k.equalsIgnoreCase("fmPhone")) {
				payload.setFmPhone(v);
			} else if (k.equalsIgnoreCase("fmPin")) {
				payload.setFmPin(v);
			} else if (k.equalsIgnoreCase("transportSpeed")) {
				payload.setTransportSpeed(v);
			} else if (k.equalsIgnoreCase("masterId")) {
				payload.setMasterId(v);
			} else if (k.equalsIgnoreCase("pickupStartTime")) {
				payload.setPickupStartTime(v);
			} else if (k.equalsIgnoreCase("pickupEndTime")) {
				payload.setPickupEndTime(v);
			} else if (k.equalsIgnoreCase("pickupSlotCode")) {
				payload.setPickupSlotCode(v);
			} else if (k.equalsIgnoreCase("dropStartTime")) {
				payload.setDropStartTime(v);
			} else if (k.equalsIgnoreCase("dropEndTime")) {
				payload.setDropEndTime(v);
			} else if (k.equalsIgnoreCase("dropSlotCode")) {
				payload.setDropSlotCode(v);
			} else if (k.equalsIgnoreCase("serviceType")) {
				payload.setServiceType(v);
			} else if (k.equalsIgnoreCase("client")) {
				//fetching client data from staging.yml file
				System.out.println(ConfigLoader.getInstance().getClient(v.toString()));
				Map<String, Object> clientDetails1 = YamlReader.getYamlValues("Client_Details.client_" +
						ConfigLoader.getInstance().getClient(v.toString()));
				payload.setClient(clientDetails1.get("name").toString());
			}
		}
		return payload;
	}

	public static Shipment2 NewShipments2(HashMap<String, Object> data) {
		Shipment2 payload = Shipment2.builder()
				.name("test")
				.client(clientDetails.get("name").toString())
				.waybill("")
				.order(getUniqueString())
				.paymentMode(paymentMode)
				.phone("9617113651")
				.pin(pin)
				.add("test add")
				.salesTaxFormAckNo("")
				.mpsChildren("")
				.mpsWeight("")
				.mpsVweight("")
				.masterid("")
				.pickupendtime("")
				.pickupslotcode("")
				.dropstarttime("")
				.dropendtime("")
				.dropslotcode("")
				.producttype(pdt)
				.servicetype("")
				.timebound("")
				.qualitycheck("")
				.taxationtype("")
				.fod("")
				.deliveryDate("")
				.nextTrialDate("")
				.movementGeography("")
				.fmMode("")
				.flowType("")
				.productCategory("")
				.speed("")
				.vas("")
				.readyToShip("")
				.internal("")
				.prime("")
				.deliveryInstructions("")
				.lrn("")
				.qc("")
				.consigneeGstAmount("")
				.sellerGstAmount("")
				.integratedGstAmount("")
				.gstCessAmount("")
				.product("")
				.productDesc("")
				.productQuantity("")
				.unit("")
				.consigneeGstRate("")
				.sellerGstRate("")
				.integratedGstRate("")
				.cessGstRate("")
				.addressType("")
				.multiInvAmt("")
				.codInstructions("")
				.buybackAddress("")
				.buybackPin("")
				.buybackDescription("")
				.shipOption("")
				.estimatedArrivalDate("")
				.shipMethod("")
				.buybackAmount("")
				.isSecure("")
				.personSpecific("")
				.addressSpecific("")
				.checkOneSecure("")
				.otp("")
				.shipmentCode("")
				.cc("")
				.cod("")
				.customs("")
				.waiver("")
				.returnStoreLocation("")
				.pickupPaymentMode("")
				.copAmount("")
				.preferredDays("")
				.expectedPcount("")
				.isOtpVerified("")
				.freightMode("")
				.freightCharge("")
				.landMark("")
				.addressId("")
				.customerId("")
				.international("")
				.isCod("")
				.build();


		for (Map.Entry<String, Object> set : data.entrySet()) {
			String k = set.getKey();
			Object v = set.getValue();

			if (k.equalsIgnoreCase("name")) {
				payload.setName(v);
			} else if (k.equalsIgnoreCase("waybill")) {
				payload.setWaybill(v);
			} else if (k.equalsIgnoreCase("order")) {
				payload.setOrder(v);
			} else if (k.equalsIgnoreCase("phone")) {
				payload.setPhone(v);
			} else if (k.equalsIgnoreCase("pin")) {
				payload.setPin(v);
			} else if (k.equalsIgnoreCase("payment_mode")) {
				payload.setPaymentMode(v);
			} else if (k.equalsIgnoreCase("salesTaxFormAckNo")) {
				payload.setSalesTaxFormAckNo(v);
			} else if (k.equalsIgnoreCase("mpsChildren")) {
				payload.setMpsChildren(v);
			} else if (k.equalsIgnoreCase("mpsWeight")) {
				payload.setMpsWeight(v);
			} else if (k.equalsIgnoreCase("mpsVweight")) {
				payload.setMpsVweight(v);
			} else if (k.equalsIgnoreCase("masterid")) {
				payload.setMasterid(v);
			} else if (k.equalsIgnoreCase("pickupendtime")) {
				payload.setPickupendtime(v);
			} else if (k.equalsIgnoreCase("pickupslotcode")) {
				payload.setPickupslotcode(v);
			} else if (k.equalsIgnoreCase("dropstarttime")) {
				payload.setDropstarttime(v);
			} else if (k.equalsIgnoreCase("dropendtime")) {
				payload.setDropendtime(v);
			} else if (k.equalsIgnoreCase("dropslotcode")) {
				payload.setDropslotcode(v);
			} else if (k.equalsIgnoreCase("producttype")) {
				payload.setProducttype(v);
			} else if (k.equalsIgnoreCase("servicetype")) {
				payload.setServicetype(v);
			} else if (k.equalsIgnoreCase("timebound")) {
				payload.setTimebound(v);
			} else if (k.equalsIgnoreCase("qualitycheck")) {
				payload.setQualitycheck(v);
			} else if (k.equalsIgnoreCase("taxationtype")) {
				payload.setTaxationtype(v);
			} else if (k.equalsIgnoreCase("fod")) {
				payload.setFod(v);
			} else if (k.equalsIgnoreCase("deliveryDate")) {
				payload.setDeliveryDate(v);
			} else if (k.equalsIgnoreCase("nextTrialDate")) {
				payload.setNextTrialDate(v);
			} else if (k.equalsIgnoreCase("movementGeography")) {
				payload.setMovementGeography(v);
			} else if (k.equalsIgnoreCase("fmMode")) {
				payload.setFmMode(v);
			} else if (k.equalsIgnoreCase("flowType")) {
				payload.setFlowType(v);
			} else if (k.equalsIgnoreCase("productCategory")) {
				payload.setProductCategory(v);
			} else if (k.equalsIgnoreCase("speed")) {
				payload.setSpeed(v);
			} else if (k.equalsIgnoreCase("vas")) {
				payload.setVas(v);
			} else if (k.equalsIgnoreCase("readyToShip")) {
				payload.setReadyToShip(v);
			} else if (k.equalsIgnoreCase("internal")) {
				payload.setInternal(v);
			} else if (k.equalsIgnoreCase("prime")) {
				payload.setPrime(v);
			} else if (k.equalsIgnoreCase("deliveryInstructions")) {
				payload.setDeliveryInstructions(v);
			} else if (k.equalsIgnoreCase("lrn")) {
				payload.setLrn(v);
			} else if (k.equalsIgnoreCase("qc")) {
				payload.setQc(v);
			} else if (k.equalsIgnoreCase("consigneeGstAmount")) {
				payload.setConsigneeGstAmount(v);
			} else if (k.equalsIgnoreCase("sellerGstAmount")) {
				payload.setSellerGstAmount(v);
			} else if (k.equalsIgnoreCase("integratedGstAmount")) {
				payload.setIntegratedGstAmount(v);
			} else if (k.equalsIgnoreCase("gstCessAmount")) {
				payload.setGstCessAmount(v);
			} else if (k.equalsIgnoreCase("product")) {
				payload.setProduct(v);
			} else if (k.equalsIgnoreCase("productDesc")) {
				payload.setProductDesc(v);
			} else if (k.equalsIgnoreCase("productQuantity")) {
				payload.setProductQuantity(v);
			} else if (k.equalsIgnoreCase("unit")) {
				payload.setUnit(v);
			} else if (k.equalsIgnoreCase("consigneeGstRate")) {
				payload.setConsigneeGstRate(v);
			} else if (k.equalsIgnoreCase("sellerGstRate")) {
				payload.setSellerGstRate(v);
			} else if (k.equalsIgnoreCase("integratedGstRate")) {
				payload.setIntegratedGstRate(v);
			} else if (k.equalsIgnoreCase("cessGstRate")) {
				payload.setCessGstRate(v);
			} else if (k.equalsIgnoreCase("addressType")) {
				payload.setAddressType(v);
			} else if (k.equalsIgnoreCase("multiInvAmt")) {
				payload.setMultiInvAmt(v);
			} else if (k.equalsIgnoreCase("codInstructions")) {
				payload.setCodInstructions(v);
			} else if (k.equalsIgnoreCase("buybackAddress")) {
				payload.setBuybackAddress(v);
			} else if (k.equalsIgnoreCase("buybackPin")) {
				payload.setBuybackPin(v);
			} else if (k.equalsIgnoreCase("buybackDescription")) {
				payload.setBuybackDescription(v);
			} else if (k.equalsIgnoreCase("shipOption")) {
				payload.setShipOption(v);
			} else if (k.equalsIgnoreCase("estimatedArrivalDate")) {
				payload.setEstimatedArrivalDate(v);
			} else if (k.equalsIgnoreCase("shipMethod")) {
				payload.setShipMethod(v);
			} else if (k.equalsIgnoreCase("buybackAmount")) {
				payload.setBuybackAmount(v);
			} else if (k.equalsIgnoreCase("isSecure")) {
				payload.setIsSecure(v);
			} else if (k.equalsIgnoreCase("personSpecific")) {
				payload.setPersonSpecific(v);
			} else if (k.equalsIgnoreCase("addressSpecific")) {
				payload.setAddressSpecific(v);
			} else if (k.equalsIgnoreCase("checkOneSecure")) {
				payload.setCheckOneSecure(v);
			} else if (k.equalsIgnoreCase("otp")) {
				payload.setOtp(v);
			} else if (k.equalsIgnoreCase("shipmentCode")) {
				payload.setShipmentCode(v);
			} else if (k.equalsIgnoreCase("cc")) {
				payload.setCc(v);
			} else if (k.equalsIgnoreCase("cod")) {
				payload.setCod(v);
			} else if (k.equalsIgnoreCase("customs")) {
				payload.setCustoms(v);
			} else if (k.equalsIgnoreCase("waiver")) {
				payload.setWaiver(v);
			} else if (k.equalsIgnoreCase("returnStoreLocation")) {
				payload.setReturnStoreLocation(v);
			} else if (k.equalsIgnoreCase("pickupPaymentMode")) {
				payload.setPickupPaymentMode(v);
			} else if (k.equalsIgnoreCase("copAmount")) {
				payload.setCopAmount(v);
			} else if (k.equalsIgnoreCase("preferredDays")) {
				payload.setPreferredDays(v);
			} else if (k.equalsIgnoreCase("expectedPcount")) {
				payload.setExpectedPcount(v);
			} else if (k.equalsIgnoreCase("isOtpVerified")) {
				payload.setIsOtpVerified(v);
			} else if (k.equalsIgnoreCase("freightMode")) {
				payload.setFreightMode(v);
			} else if (k.equalsIgnoreCase("freightCharge")) {
				payload.setFreightCharge(v);
			} else if (k.equalsIgnoreCase("landMark")) {
				payload.setLandMark(v);
			} else if (k.equalsIgnoreCase("addressId")) {
				payload.setAddressId(v);
			} else if (k.equalsIgnoreCase("customerId")) {
				payload.setCustomerId(v);
			} else if (k.equalsIgnoreCase("international")) {
				payload.setInternational(v);
			}else if(k.equalsIgnoreCase("isCod")) {
				payload.setIsCod(v);
			} else if (k.equalsIgnoreCase("client")) {
				//fetching client data from staging.yml file
				System.out.println(ConfigLoader.getInstance().getClient(v.toString()));
				Map<String, Object> clientDetails1 = YamlReader.getYamlValues("Client_Details.client_" +
						ConfigLoader.getInstance().getClient(v.toString()));
				payload.setClient(clientDetails1.get("name").toString());
			}
		}
		return payload;
	}

	public static Shipment3 NewShipments3(HashMap<String, Object> data) {
		Shipment3 payload = Shipment3.builder()
				.name("test")
				.client(clientDetails.get("name").toString())
				.waybill("")
				.order(getUniqueString())
				.paymentMode(paymentMode)
				.producttype(pdt)
				.phone("9617113651")
				.pin(pin)
				.add("test add")
				.e2eId("")
				.chequeAdd("")
				.chequePhone("")
				.chequeaddress("")
				.chequephones("")
				.chequecitys("")
				.chequeCity("")
				.chequeState("")
				.chequestates("")
				.chequePin("")
				.chequepincode("")
				.chequeCountry("")
				.chequecountrys("")
				.supplyType("")
				.subSupplyDesc("")
				.transactionType("")
				.otherValue("")
				.cessNonAdvolValue("")
				.cessNonAdvolRate("")
				.provider("")
				.hyperlocal("")
				.plasticPackaging("")
				.flatRateType("")
				.holdLocation("")
				.sameDayClear("")
				.fmPickupType("")
				.fmUcid("")
				.insured("")
				.hasDocument("")
				.verifiedAdd("")
				.einvQr("")
				.manifestationUser("")
				.itemDesc("")
				.invoiceValue("")
				.supplySubType("")
				.documentType("")
				.documentDate("")
				.billingAdd("")
				.billingPin("")
				.supplytypes("")
				.subSupplyType("")
				.othervalues("")
				.cessnonadvolvalues("")
				.priNumber("")
				.uniqueCustomerId("")
				.build();
		//map for data entry set for above payload
		for (Map.Entry<String, Object> set : data.entrySet()) {
			String k = set.getKey();
			Object v = set.getValue();

			if (k.equalsIgnoreCase("name")) {
				payload.setName(v);
			} else if (k.equalsIgnoreCase("waybill")) {
				payload.setWaybill(v);
			} else if (k.equalsIgnoreCase("order")) {
				payload.setOrder(v);
			} else if (k.equalsIgnoreCase("paymentMode")) {
				payload.setPaymentMode(v);
			} else if (k.equalsIgnoreCase("producttype")) {
				payload.setProducttype(v);
			} else if (k.equalsIgnoreCase("phone")) {
				payload.setPhone(v);
			} else if (k.equalsIgnoreCase("pin")) {
				payload.setPin(v);
			} else if (k.equalsIgnoreCase("e2eId")) {
				payload.setE2eId(v);
			} else if (k.equalsIgnoreCase("chequeAdd")) {
				payload.setChequeAdd(v);
			} else if (k.equalsIgnoreCase("chequePhone")) {
				payload.setChequePhone(v);
			} else if (k.equalsIgnoreCase("chequeaddress")) {
				payload.setChequeaddress(v);
			} else if (k.equalsIgnoreCase("chequephones")) {
				payload.setChequephones(v);
			} else if (k.equalsIgnoreCase("chequecitys")) {
				payload.setChequecitys(v);
			} else if (k.equalsIgnoreCase("chequeCity")) {
				payload.setChequeCity(v);
			} else if (k.equalsIgnoreCase("chequeState")) {
				payload.setChequeState(v);
			} else if (k.equalsIgnoreCase("chequestates")) {
				payload.setChequestates(v);
			} else if (k.equalsIgnoreCase("chequePin")) {
				payload.setChequePin(v);
			} else if (k.equalsIgnoreCase("chequepincode")) {
				payload.setChequepincode(v);
			} else if (k.equalsIgnoreCase("chequeCountry")) {
				payload.setChequeCountry(v);
			} else if (k.equalsIgnoreCase("chequecountrys")) {
				payload.setChequecountrys(v);
			} else if (k.equalsIgnoreCase("supplyType")) {
				payload.setSupplyType(v);
			} else if (k.equalsIgnoreCase("subSupplyDesc")) {
				payload.setSubSupplyDesc(v);
			} else if (k.equalsIgnoreCase("transactionType")) {
				payload.setTransactionType(v);
			} else if (k.equalsIgnoreCase("otherValue")) {
				payload.setOtherValue(v);
			} else if (k.equalsIgnoreCase("cessNonAdvolValue")) {
				payload.setCessNonAdvolValue(v);
			} else if (k.equalsIgnoreCase("cessNonAdvolRate")) {
				payload.setCessNonAdvolRate(v);
			} else if (k.equalsIgnoreCase("provider")) {
				payload.setProvider(v);
			} else if (k.equalsIgnoreCase("hyperlocal")) {
				payload.setHyperlocal(v);
			} else if (k.equalsIgnoreCase("plasticPackaging")) {
				payload.setPlasticPackaging(v);
			} else if (k.equalsIgnoreCase("flatRateType")) {
				payload.setFlatRateType(v);
			} else if (k.equalsIgnoreCase("holdLocation")) {
				payload.setHoldLocation(v);
			} else if (k.equalsIgnoreCase("sameDayClear")) {
				payload.setSameDayClear(v);
			} else if (k.equalsIgnoreCase("fmPickupType")) {
				payload.setFmPickupType(v);
			} else if (k.equalsIgnoreCase("fmUcid")) {
				payload.setFmUcid(v);
			} else if (k.equalsIgnoreCase("insured")) {
				payload.setInsured(v);
			} else if (k.equalsIgnoreCase("hasDocument")) {
				payload.setHasDocument(v);
			} else if (k.equalsIgnoreCase("verifiedAdd")) {
				payload.setVerifiedAdd(v);
			} else if (k.equalsIgnoreCase("einvQr")) {
				payload.setEinvQr(v);
			} else if (k.equalsIgnoreCase("manifestationUser")) {
				payload.setManifestationUser(v);
			} else if (k.equalsIgnoreCase("itemDesc")) {
				payload.setItemDesc(v);
			} else if (k.equalsIgnoreCase("invoiceValue")) {
				payload.setInvoiceValue(v);
			} else if (k.equalsIgnoreCase("supplySubType")) {
				payload.setSupplySubType(v);
			} else if (k.equalsIgnoreCase("documentType")) {
				payload.setDocumentType(v);
			} else if (k.equalsIgnoreCase("documentDate")) {
				payload.setDocumentDate(v);
			} else if (k.equalsIgnoreCase("billingAdd")) {
				payload.setBillingAdd(v);
			} else if (k.equalsIgnoreCase("billingPin")) {
				payload.setBillingPin(v);
			} else if (k.equalsIgnoreCase("supplytypes")) {
				payload.setSupplytypes(v);
			} else if (k.equalsIgnoreCase("subSupplyType")) {
				payload.setSubSupplyType(v);
			} else if (k.equalsIgnoreCase("transactionType")) {
				payload.setTransactionType(v);
			} else if (k.equalsIgnoreCase("othervalue")) {
				payload.setOthervalues(v);
			} else if (k.equalsIgnoreCase("cessnonadvolvalue")) {
				payload.setCessnonadvolvalues(v);
			} //add for priNumber
			else if (k.equalsIgnoreCase("priNumber")) {
				payload.setPriNumber(v);
			} else if (k.equalsIgnoreCase("uniqueCustomerId")) {
				payload.setUniqueCustomerId(v);
			}
			else if (k.equalsIgnoreCase("client")) {
				//fetching client data from staging.yml file
				System.out.println(ConfigLoader.getInstance().getClient(v.toString()));
				Map<String, Object> clientDetails1 = YamlReader.getYamlValues("Client_Details.client_" +
						ConfigLoader.getInstance().getClient(v.toString()));
				payload.setClient(clientDetails1.get("name").toString());
			}
		}
		return payload;
	}

	//creating payload for CreateMPS with master and child payload
	public static CreateMPS createMPSApiReqGen(HashMap<String, Object> data) {
		CreateMPS body = CreateMPS.builder()
				.shipments(new ArrayList<ShipmentMps>(
						Arrays.asList(ShipmentMps.builder()
						.lrn("")
						.internal("")
						.sst("")
						.add("test add")
						.fod("")
						.documentType("")
						.productType(pdt)
						.country("")
						.city("")
						.mpsAmount("")
						.mpsChildren("")
						.codAmount("")
						.weight("")
						.taxationType("")
						.shipmentType("")
						.source("")
						.supplier("")
						.name("test")
						.phone("9617113651")
						.pin(pin)
						.state("")
						.productsDesc("")
						.order(getUniqueString())
						.invoiceReference("")
						.sellerCst("")
						.sellerTin("")
						.sellerGstTin("")
						.clientGstTin("")
						.totalAmount("")
						.client(clientDetails.get("name").toString())
						.orderDate("")
						.mpsAmount("")
						.masterId("")
						.waybill("")
						.ewbn("")
						.country("")
						.sst("")
						.hsnCode("")
						.supplySubType("")
						.documentType("")
						.sellerName("")
						.documentNumber("")
						.multiInvAmt("")
						.internal("")
						.paymentMode("prepaid")
						.build())))

				.build();


		//write map for data entry set for above payload
		//including all keys under ShipmentMps dict
		for (Map.Entry<String, Object> set : data.entrySet()) {
			String k = set.getKey();
			Object v = set.getValue();

			if (k.equalsIgnoreCase("lrn")) {
				body.getShipments().get(0).setLrn(v);
			} else if (k.equalsIgnoreCase("internal")) {
				body.getShipments().get(0).setInternal(v);
			} else if (k.equalsIgnoreCase("sst")) {
				body.getShipments().get(0).setSst(v);
			} else if (k.equalsIgnoreCase("add")) {
				body.getShipments().get(0).setAdd(v);
			} else if (k.equalsIgnoreCase("fod")) {
				body.getShipments().get(0).setFod(v);
			} else if (k.equalsIgnoreCase("documentType")) {
				body.getShipments().get(0).setDocumentType(v);
			} else if (k.equalsIgnoreCase("productType")) {
				body.getShipments().get(0).setProductType(v);
			} else if (k.equalsIgnoreCase("country")) {
				body.getShipments().get(0).setCountry(v);
			} else if (k.equalsIgnoreCase("city")) {
				body.getShipments().get(0).setCity(v);
			} else if (k.equalsIgnoreCase("mpsAmount")) {
				body.getShipments().get(0).setMpsAmount(v);
			} else if (k.equalsIgnoreCase("mpsChildren")) {
				body.getShipments().get(0).setMpsChildren(v);
			} else if (k.equalsIgnoreCase("codAmount")) {
				body.getShipments().get(0).setCodAmount(v);
			} else if (k.equalsIgnoreCase("weight")) {
				body.getShipments().get(0).setWeight(v);
			} else if (k.equalsIgnoreCase("taxationType")) {
				body.getShipments().get(0).setTaxationType(v);
			} else if (k.equalsIgnoreCase("shipmentType")) {
				body.getShipments().get(0).setShipmentType(v);
			} else if (k.equalsIgnoreCase("source")) {
				body.getShipments().get(0).setSource(v);
			} else if (k.equalsIgnoreCase("supplier")) {
				body.getShipments().get(0).setSupplier(v);
			} else if (k.equalsIgnoreCase("name")) {
				body.getShipments().get(0).setName(v);
			} else if (k.equalsIgnoreCase("phone")) {
				body.getShipments().get(0).setPhone(v);
			} else if (k.equalsIgnoreCase("pin")) {
				body.getShipments().get(0).setPin(v);
			} else if (k.equalsIgnoreCase("state")) {
				body.getShipments().get(0).setState(v);
			} else if (k.equalsIgnoreCase("productsDesc")) {
				body.getShipments().get(0).setProductsDesc(v);
			} else if (k.equalsIgnoreCase("order")) {
				body.getShipments().get(0).setOrder(v);
			} else if (k.equalsIgnoreCase("invoiceReference")) {
				body.getShipments().get(0).setInvoiceReference(v);
			} else if (k.equalsIgnoreCase("sellerCst")) {
				body.getShipments().get(0).setSellerCst(v);
			} else if (k.equalsIgnoreCase("sellerTin")) {
				body.getShipments().get(0).setSellerTin(v);
			} else if (k.equalsIgnoreCase("sellerGstTin")) {
				body.getShipments().get(0).setSellerGstTin(v);
			} else if (k.equalsIgnoreCase("clientGstTin")) {
				body.getShipments().get(0).setClientGstTin(v);
			} else if (k.equalsIgnoreCase("totalAmount")) {
				body.getShipments().get(0).setTotalAmount(v);
			} else if (k.equalsIgnoreCase("client")) {
				body.getShipments().get(0).setClient(v);
			} else if (k.equalsIgnoreCase("orderDate")) {
				body.getShipments().get(0).setOrderDate(v);
			} else if (k.equalsIgnoreCase("mpsAmount")) {
				body.getShipments().get(0).setMpsAmount(v);
			} else if (k.equalsIgnoreCase("masterId")) {
				body.getShipments().get(0).setMasterId(v);
			} else if (k.equalsIgnoreCase("waybill")) {
				body.getShipments().get(0).setWaybill(v);
			} else if (k.equalsIgnoreCase("ewbn")) {
				body.getShipments().get(0).setEwbn(v);
			} else if (k.equalsIgnoreCase("country")) {
				body.getShipments().get(0).setCountry(v);
			} else if (k.equalsIgnoreCase("sst")) {
				body.getShipments().get(0).setSst(v);
			} else if (k.equalsIgnoreCase("hsnCode")) {
				body.getShipments().get(0).setHsnCode(v);
			} else if (k.equalsIgnoreCase("supplySubType")) {
				body.getShipments().get(0).setSupplySubType(v);
			} else if (k.equalsIgnoreCase("documentType")) {
				body.getShipments().get(0).setDocumentType(v);
			} else if (k.equalsIgnoreCase("sellerName")) {
				body.getShipments().get(0).setSellerName(v);
			} else if (k.equalsIgnoreCase("documentNumber")) {
				body.getShipments().get(0).setDocumentNumber(v);
			} else if (k.equalsIgnoreCase("multiInvAmt")) {
				body.getShipments().get(0).setMultiInvAmt(v);
			} else if (k.equalsIgnoreCase("internal")) {
				body.getShipments().get(0).setInternal(v);
			}
		}
		return body;
	}

	public static multiItemRequest multiItemApiReqGen(HashMap<String, Object> data) {
		List<ItemList> listShipments = new ArrayList<>();
		listShipments.add(multiItemApiReqGenItem(data));
		listShipments.add(multiItemApiReqGenItem(data));
		multiItemRequest body = multiItemRequest.builder()
				.pickupLocation(PickupLocation.builder()
						.city("Gurgaon")
						.name("test")
						.build())
				//Shipment builder for multiItem request
				.shipments(new ArrayList<MultiItemShipment>(Arrays.asList(MultiItemShipment.builder()
						.totalAmount(10)
						.add("test add")
						.city("")
						.country("")
						.state("")
						.clientGstTin("")
						.consigneeGstTin("")
						.invoiceReference("")
						.sellerGstTin("")
						.sellerName("")
						.phone("9617113651")
						.pin(pin)
						.dangerousGood(false)
						.returnCity("")
						.returnCountry("")
						.returnName("")
						.returnPhone("")
						.returnPin("")
						.returnState("")
						.supplier("")
						.billableWeight(10)
						.dimensions("")
						.volumetric(10)
						.weight("")
						.documentType("")
						.documentDate("")
						.supplySubType(10)
						//add builder for item list using pojo
						.items(listShipments)
						.build())))
				.build();
		//write map for data entry set for above payload
		//including all keys for Shipment dict for multiItem api
		for (Map.Entry<String, Object> set : data.entrySet()) {
			String k = set.getKey();
			Object v = set.getValue();

			if (k.equalsIgnoreCase("totalAmount")) {
				body.getShipments().get(0).setTotalAmount(v);
			} else if (k.equalsIgnoreCase("add")) {
				body.getShipments().get(0).setAdd(v);
			} else if (k.equalsIgnoreCase("city")) {
				body.getShipments().get(0).setCity(v);
			} else if (k.equalsIgnoreCase("country")) {
				body.getShipments().get(0).setCountry(v);
			} else if (k.equalsIgnoreCase("state")) {
				body.getShipments().get(0).setState(v);
			} else if (k.equalsIgnoreCase("clientGstTin")) {
				body.getShipments().get(0).setClientGstTin(v);
			} else if (k.equalsIgnoreCase("consigneeGstTin")) {
				body.getShipments().get(0).setConsigneeGstTin(v);
			} else if (k.equalsIgnoreCase("invoiceReference")) {
				body.getShipments().get(0).setInvoiceReference(v);
			} else if (k.equalsIgnoreCase("sellerGstTin")) {
				body.getShipments().get(0).setSellerGstTin(v);
			} else if (k.equalsIgnoreCase("sellerName")) {
				body.getShipments().get(0).setSellerName(v);
			} else if (k.equalsIgnoreCase("phone")) {
				body.getShipments().get(0).setPhone(v);
			} else if (k.equalsIgnoreCase("pin")) {
				body.getShipments().get(0).setPin(v);
			} else if (k.equalsIgnoreCase("dangerousGood")) {
				body.getShipments().get(0).setDangerousGood(v);
			} else if (k.equalsIgnoreCase("returnCity")) {
				body.getShipments().get(0).setReturnCity(v);
			} else if (k.equalsIgnoreCase("returnCountry")) {
				body.getShipments().get(0).setReturnCountry(v);
			} else if (k.equalsIgnoreCase("returnName")) {
				body.getShipments().get(0).setReturnName(v);
			} else if (k.equalsIgnoreCase("returnPhone")) {
				body.getShipments().get(0).setReturnPhone(v);
			} else if (k.equalsIgnoreCase("returnPin")) {
				body.getShipments().get(0).setReturnPin(v);
			} else if (k.equalsIgnoreCase("returnState")) {
				body.getShipments().get(0).setReturnState(v);
			} else if (k.equalsIgnoreCase("supplier")) {
				body.getShipments().get(0).setSupplier(v);
			} else if (k.equalsIgnoreCase("billableWeight")) {
				body.getShipments().get(0).setBillableWeight(v);
			} else if (k.equalsIgnoreCase("dimensions")) {
				body.getShipments().get(0).setDimensions(v);
			} else if (k.equalsIgnoreCase("volumetric")) {
				body.getShipments().get(0).setVolumetric(v);
			} else if (k.equalsIgnoreCase("weight")) {
				body.getShipments().get(0).setWeight(v);
			} else if (k.equalsIgnoreCase("documentType")) {
				body.getShipments().get(0).setDocumentType(v);
			} else if (k.equalsIgnoreCase("documentDate")) {
				body.getShipments().get(0).setDocumentDate(v);
			} else if (k.equalsIgnoreCase("supplySubType")) {
				body.getShipments().get(0).setSupplySubType(v);
			}
		}
		return body;
	}






	//creating payload for multi item api
	public static ItemList multiItemApiReqGenItem(HashMap<String, Object> data) {
		ItemList body = ItemList.builder()
				.description("test")
				.product("test")
				.hsnCode("test")
				.ewbn("")
				.documentNumber("test")
				.taxableAmount(10.0)
				.quantity(10)
				.unit("test")
				.consigneeGstRate(10.0)
				.sellerGstRate(10.0)
				.integratedGstRate(10.0)
				.cessGstRate(10.0)

				.build();
		//write map for data entry set for above payload
		//including all keys under ItemList dict for multiItem api
		for (Map.Entry<String, Object> set : data.entrySet()) {
			String k = set.getKey();
			Object v = set.getValue();

			if (k.equalsIgnoreCase("description")) {
				body.setDescription(v);
			} else if (k.equalsIgnoreCase("product")) {
				body.setProduct(v);
			} else if (k.equalsIgnoreCase("hsnCode")) {
				body.setHsnCode(v);
			} else if (k.equalsIgnoreCase("ewbn")) {
				body.setEwbn(v);
			} else if (k.equalsIgnoreCase("documentNumber")) {
				body.setDocumentNumber(v);
			} else if (k.equalsIgnoreCase("taxableAmount")) {
				body.setTaxableAmount(v);
			} else if (k.equalsIgnoreCase("quantity")) {
				body.setQuantity(v);
			} else if (k.equalsIgnoreCase("unit")) {
				body.setUnit(v);
			} else if (k.equalsIgnoreCase("consigneeGstRate")) {
				body.setConsigneeGstRate(v);
			} else if (k.equalsIgnoreCase("sellerGstRate")) {
				body.setSellerGstRate(v);
			} else if (k.equalsIgnoreCase("integratedGstRate")) {
				body.setIntegratedGstRate(v);
			} else if (k.equalsIgnoreCase("cessGstRate")) {
				body.setCessGstRate(v);
			}
		}

		return body;
	}

	//creating payload for only mandatory keys
	//mandatory keys = name, client, pin, phone, address, productType, paymentMode, order
	public static MandatoryKeys MandatoryKeys(HashMap<String, Object> data) {
		MandatoryKeys payload = MandatoryKeys.builder()
				//add shipments list builder
				.shipments(new ArrayList<Shipments>(Arrays.asList(Shipments.builder()
						.add("")
						.pin(pin)
						.phone("9617113651")
						.productType(pdt)
						.name("tset")
						.paymentMode(paymentMode)
						.order(getUniqueString())
						.client(clientDetails.get("name").toString())
						.build())
				))
				.build();
		//write map for data entry set for above payload
		//including all keys under shipment dict

		for (Map.Entry<String, Object> set : data.entrySet()) {
			String k = set.getKey();
			Object v = set.getValue();

			if (k.equalsIgnoreCase("name")) {
				payload.getShipments().get(0).setName(v);
			} else if (k.equalsIgnoreCase("pin")) {
				payload.getShipments().get(0).setPin(v);
			} else if (k.equalsIgnoreCase("phone")) {
				payload.getShipments().get(0).setPhone(v);
			} else if (k.equalsIgnoreCase("productType")) {
				payload.getShipments().get(0).setProductType(v);
			} else if (k.equalsIgnoreCase("paymentMode")) {
				payload.getShipments().get(0).setPaymentMode(v);
			} else if (k.equalsIgnoreCase("order")) {
				payload.getShipments().get(0).setOrder(v);
			} else if (k.equalsIgnoreCase("client")) {
				//fetching client data from staging.yml file
				System.out.println(ConfigLoader.getInstance().getClient(v.toString()));
				Map<String, Object> clientDetails1 = YamlReader.getYamlValues("Client_Details.client_" +
						ConfigLoader.getInstance().getClient(v.toString()));
				payload.getShipments().get(0).setClient(clientDetails1.get("name").toString());
			}
		}
		return payload;
	}

	//creating payload for client update api
	//API request = clientCreateUpdateNew
	//request pojo = ClientUpdateNew
	public static ClientUpdateNew ClientUpdateNew(HashMap<String, Object> data) {
		ClientUpdateNew payload = ClientUpdateNew.builder()
				//add clientUpdateNew builder keys

				.clientName("ManifestClient")
				.clientLastName("cl")
				.clientFirstName("cl")
				.salesforceId("123")
				.billingMode("Surface")
				.xrayAmountLimit(7000)
				.clientType("")
				.billingMethod("postpaid")
				.walletProvider("DEL-MILES")
				.phone("9617113651")
				.walletNotificationMobile("9900972650")
				.walletNotificationEmail("qatester9119@gmail.com")
				.paymentLink(true)
				//add builder for services
				.services(Services.builder()
						.cod("COD")
						.pickup("Pickup")
						.Cash("")
						.NoData("")
						.prePaid("")
						.repl("")
						.build() )
				.qrPayment(false)
				.isPrepaidService(true)
				.productType("")
				.divisor(400)
				.weightStatus("verified")
				.clientUuid("cms::client::b77b4ca4-ff24-11eb-9caf-0gdewiufgewuift")
				.miscellaneousData(MiscellaneousData.builder()
						//add builder for miscellaneous data
						.parentAccountName("Flipkart")
						.parentAccountId("12345678")
						.creditLimitTrips(1)
						.creditPeriod(50)
						.creditNoteLimit(1000)
						.isClientActive(true)
						.pricingExp("2021-08-31")
						.motDivisor(MotDivisor.builder()
								.surface(4444.09)
								.build())
						.build())
				.hoState("UTTAR PRADESH")
				.gstStates(GstStates.builder()
						.uttarpradesh("07ADTPA8419R1ZP")
						.bihar("07ADTPA8419R1RT")
						.haryana("12ASERA2398W1RY")
						.build())
				.billingClientConfig("drop_state")
				.billingDlvConfig("dlv_ho")
				.gstRate(0)
				.gstBillingType("rcm_billing")
				.movementConfig("warehouse_state_gstin")
				//add all keys for clientUpdateNew builder
				.failManifest(false)
				.captureClientOtp(false)
				.enableIvr(false)
				.enableManifestFailure(false)
				.enableSmsService(false)
				.lock(false)
				.isPrepaid(false)
				.mpsService(false)
				.securePickup(false)
				.lineOfBusiness(false)
				.onDemandFail(false)
				.weightCaptureEnable(false)
				.verifiedAdd(false)
				.fragileShipment(false)
				.weightCaptureEnable(false)
				.build();
		//write map for data entry set for above payload
		//including all keys under clientUpdateNew dict
		for (Map.Entry<String, Object> set : data.entrySet()) {
			String k = set.getKey();
			Object v = set.getValue();
			if (k.equalsIgnoreCase("clientName")) {
				payload.setClientName(v);
			} else if (k.equalsIgnoreCase("clientLastName")) {
				payload.setClientLastName(v);
			} else if (k.equalsIgnoreCase("clientFirstName")) {
				payload.setClientFirstName(v);
			} else if (k.equalsIgnoreCase("salesforceId")) {
				payload.setSalesforceId(v);
			} else if (k.equalsIgnoreCase("billingMode")) {
				payload.setBillingMode(v);
			} else if (k.equalsIgnoreCase("xrayAmountLimit")) {
				payload.setXrayAmountLimit(v);
			} else if (k.equalsIgnoreCase("clientType")) {
				payload.setClientType(v);
			} else if (k.equalsIgnoreCase("billingMethod")) {
				payload.setBillingMethod(v);
			} else if (k.equalsIgnoreCase("walletProvider")) {
				payload.setWalletProvider(v);
			} else if (k.equalsIgnoreCase("phone")) {
				payload.setPhone(v);
			} else if (k.equalsIgnoreCase("walletNotificationMobile")) {
				payload.setWalletNotificationMobile(v);
			} else if (k.equalsIgnoreCase("walletNotificationEmail")) {
				payload.setWalletNotificationEmail(v);
			} else if (k.equalsIgnoreCase("paymentLink")) {
				payload.setPaymentLink(v);
			} else if (k.equalsIgnoreCase("cod")) {
				payload.getServices().setCod(v);
			} else if (k.equalsIgnoreCase("pickup")) {
				payload.getServices().setPickup(v);
			} else if (k.equalsIgnoreCase("Cash")) {
				payload.getServices().setCash(v);
			} else if (k.equalsIgnoreCase("NoData")) {
				payload.getServices().setNoData(v);
			} else if (k.equalsIgnoreCase("prePaid")) {
				payload.getServices().setPrePaid(v);
			} else if (k.equalsIgnoreCase("repl")) {
				payload.getServices().setRepl(v);
			} else if (k.equalsIgnoreCase("qrPayment")) {
				payload.setQrPayment(v);
			} else if (k.equalsIgnoreCase("isPrepaidService")) {
				payload.setIsPrepaidService(v);
			} else if (k.equalsIgnoreCase("productType")) {
				payload.setProductType(v);
			} else if (k.equalsIgnoreCase("divisor")) {
				payload.setDivisor(v);
			} else if (k.equalsIgnoreCase("weightStatus")) {
				payload.setWeightStatus(v);
			} else if (k.equalsIgnoreCase("clientUuid")) {
				payload.setClientUuid(v);
			} else if (k.equalsIgnoreCase("parentAccountName")) {
				payload.getMiscellaneousData().setParentAccountName(v);
			} else if (k.equalsIgnoreCase("parentAccountId")) {
				payload.getMiscellaneousData().setParentAccountId(v);
			} else if (k.equalsIgnoreCase("creditLimitTrips")) {
				payload.getMiscellaneousData().setCreditLimitTrips(v);
			} else if (k.equalsIgnoreCase("creditPeriod")) {
				payload.getMiscellaneousData().setCreditPeriod(v);
			} else if (k.equalsIgnoreCase("creditNoteLimit")) {
				payload.getMiscellaneousData().setCreditNoteLimit(v);
			} else if (k.equalsIgnoreCase("isClientActive")) {
				payload.getMiscellaneousData().setIsClientActive(v);
			} else if (k.equalsIgnoreCase("pricingExp")) {
				payload.getMiscellaneousData().setPricingExp(v);
			} else if (k.equalsIgnoreCase("surface")) {
				payload.getMiscellaneousData().getMotDivisor().setSurface(v);
			} else if (k.equalsIgnoreCase("hoState")) {
				payload.setHoState(v);
			} else if (k.equalsIgnoreCase("uttarpradesh")) {
				payload.getGstStates().setUttarpradesh(v);
			} else if (k.equalsIgnoreCase("bihar")) {
				payload.getGstStates().setBihar(v);
			} else if (k.equalsIgnoreCase("haryana")) {
				payload.getGstStates().setHaryana(v);
			} else if (k.equalsIgnoreCase("billingClientConfig")) {
				payload.setBillingClientConfig(v);
			} else if (k.equalsIgnoreCase("billingDlvConfig")) {
				payload.setBillingDlvConfig(v);
			} else if (k.equalsIgnoreCase("gstRate")) {
				payload.setGstRate(v);
			} else if (k.equalsIgnoreCase("gstBillingType")) {
				payload.setGstBillingType(v);
			}
		}

		return payload;
	}

	//creating payload for meesho1
	//API request = meesho1
	//request pojo = meesho1
	public static meesho1 meesho1(HashMap<String, Object> data) {
		List<ItemMeesho> listShipments = new ArrayList<>();
		listShipments.add(meeshoItem(data));
		listShipments.add(meeshoItem(data));
		meesho1 body = meesho1.builder()
				.pickupLocation(PickupLocationMeesho.builder()
						.shpName("Gurgaon")
						.name("test")
						.build())
				//Shipment builder for multiItem request
				.shipments(new ArrayList<ShipmentMeesho>(Arrays.asList(ShipmentMeesho.builder()
						.totalAmount(10)
						.add("test add")
						.country("")
						.clientGstTin("")
						.consigneeGstTin("")
						.invoiceReference("")
						.sellerGstTin("")
						.sellerName("")
						.phone("9617113651")
						.pin(pin)
						.supplier("")
						.billableWeight(10)
						.dimensions("")
						.volumetric(10)
						.documentType("")
						.extraParameters(ExtraParametersMeesho.builder()
								//add builder for extra parameters
								.returnReason("123")
								.build())
						.qc(Qc.builder()
								.numItem(1)
								.item(listShipments)
								.build())

						.build())))
				.build();
		//write map for data entry set for above payload
		//including all keys for Shipment dict for meesho1 api
		for (Map.Entry<String, Object> set : data.entrySet()) {
			String k = set.getKey();
			Object v = set.getValue();

			if (k.equalsIgnoreCase("totalAmount")) {
				body.getShipments().get(0).setTotalAmount(v);
			} else if (k.equalsIgnoreCase("add")) {
				body.getShipments().get(0).setAdd(v);
			} else if (k.equalsIgnoreCase("country")) {
				body.getShipments().get(0).setCountry(v);
			} else if (k.equalsIgnoreCase("clientGstTin")) {
				body.getShipments().get(0).setClientGstTin(v);
			} else if (k.equalsIgnoreCase("consigneeGstTin")) {
				body.getShipments().get(0).setConsigneeGstTin(v);
			} else if (k.equalsIgnoreCase("invoiceReference")) {
				body.getShipments().get(0).setInvoiceReference(v);
			} else if (k.equalsIgnoreCase("sellerGstTin")) {
				body.getShipments().get(0).setSellerGstTin(v);
			} else if (k.equalsIgnoreCase("sellerName")) {
				body.getShipments().get(0).setSellerName(v);
			} else if (k.equalsIgnoreCase("phone")) {
				body.getShipments().get(0).setPhone(v);
			} else if (k.equalsIgnoreCase("pin")) {
				body.getShipments().get(0).setPin(v);
			} else if (k.equalsIgnoreCase("supplier")) {
				body.getShipments().get(0).setSupplier(v);
			} else if (k.equalsIgnoreCase("billableWeight")) {
				body.getShipments().get(0).setBillableWeight(v);
			} else if (k.equalsIgnoreCase("dimensions")) {
				body.getShipments().get(0).setDimensions(v);
			} else if (k.equalsIgnoreCase("volumetric")) {
				body.getShipments().get(0).setVolumetric(v);
			} else if (k.equalsIgnoreCase("documentType")) {
				body.getShipments().get(0).setDocumentType(v);
			} else if (k.equalsIgnoreCase("returnReason")) {
				body.getShipments().get(0).getExtraParameters().setReturnReason(v);
			} else if (k.equalsIgnoreCase("numItem")) {
				body.getShipments().get(0).getQc().setNumItem(v);
			}
		}
		return body;





	}

	public static ItemMeesho meeshoItem(HashMap<String, Object> data) {
		ItemMeesho body = ItemMeesho.builder()
				.descr("test")
				.ean(12345)
				.brand("test")
				.si("test")
				.imei(12345)
				.reason("10")
				.images("test")
				.serial(10)
				.pcat("10")
				.itemQuantity(10)
				.build();
		//write map for data entry set for above payload
		//including all keys under ItemMeesho dict for Meesho1 api
		for (Map.Entry<String, Object> set : data.entrySet()) {
			String k = set.getKey();
			Object v = set.getValue();

			if (k.equalsIgnoreCase("descr")) {
				body.setDescr(v);
			} else if (k.equalsIgnoreCase("ean")) {
				body.setEan(v);
			} else if (k.equalsIgnoreCase("brand")) {
				body.setBrand(v);
			} else if (k.equalsIgnoreCase("si")) {
				body.setSi(v);
			} else if (k.equalsIgnoreCase("imei")) {
				body.setImei(v);
			} else if (k.equalsIgnoreCase("reason")) {
				body.setReason(v);
			} else if (k.equalsIgnoreCase("images")) {
				body.setImages(v);
			} else if (k.equalsIgnoreCase("serial")) {
				body.setSerial(v);
			} else if (k.equalsIgnoreCase("pcat")) {
				body.setPcat(v);
			} else if (k.equalsIgnoreCase("itemQuantity")) {
				body.setItemQuantity(v);
			}
		}


		return body;
	}

	//creating payload for meesho3
	//API request = Meesho3
	//request pojo = Meesho3
	public static meesho3 Meesho3(HashMap<String, Object> data) {
		List<CustomQc> listShipments = new ArrayList<>();
		listShipments.add(customQc(data));
		listShipments.add(customQc(data));
		meesho3 body = meesho3.builder()
				.shipments(new ArrayList<com.delhivery.Express.pojo.Meesho3.Request.Shipment>(Arrays.asList(com.delhivery.Express.pojo.Meesho3.Request.Shipment.builder()
						.client("")
						.totalAmount("")
						.add("test add")
						.country("")
						.sellerGstTin("")
						.sellerName("")
						.phone("9617113651")
						.pin(pin)
						.name("")
						.city("")
						.returnAdd("")
						.productsDesc("")
						.quantity("")
						.order("")
						.orderDate("")
						.paymentMode("")
						.returnCountry("")
						.returnPhone("")
						.returnState("")
						.customQc(listShipments)
						.shippingMode("")
						.weight("")
						.state("")
						.build())))
				.build();
		//write map for data entry set for above payload
		//including all keys for Shipment dict for meesho3 api
		for (Map.Entry<String, Object> set : data.entrySet()) {
			String k = set.getKey();
			Object v = set.getValue();

			if (k.equalsIgnoreCase("client")) {
				body.getShipments().get(0).setClient(v);
			} else if (k.equalsIgnoreCase("totalAmount")) {
				body.getShipments().get(0).setTotalAmount(v);
			} else if (k.equalsIgnoreCase("add")) {
				body.getShipments().get(0).setAdd(v);
			} else if (k.equalsIgnoreCase("country")) {
				body.getShipments().get(0).setCountry(v);
			} else if (k.equalsIgnoreCase("sellerGstTin")) {
				body.getShipments().get(0).setSellerGstTin(v);
			} else if (k.equalsIgnoreCase("sellerName")) {
				body.getShipments().get(0).setSellerName(v);
			} else if (k.equalsIgnoreCase("phone")) {
				body.getShipments().get(0).setPhone(v);
			} else if (k.equalsIgnoreCase("pin")) {
				body.getShipments().get(0).setPin(v);
			} else if (k.equalsIgnoreCase("name")) {
				body.getShipments().get(0).setName(v);
			} else if (k.equalsIgnoreCase("city")) {
				body.getShipments().get(0).setCity(v);
			} else if (k.equalsIgnoreCase("returnAdd")) {
				body.getShipments().get(0).setReturnAdd(v);
			} else if (k.equalsIgnoreCase("productsDesc")) {
				body.getShipments().get(0).setProductsDesc(v);
			} else if (k.equalsIgnoreCase("quantity")) {
				body.getShipments().get(0).setQuantity(v);
			} else if (k.equalsIgnoreCase("order")) {
				body.getShipments().get(0).setOrder(v);
			} else if (k.equalsIgnoreCase("orderDate")) {
				body.getShipments().get(0).setOrderDate(v);
			} else if (k.equalsIgnoreCase("paymentMode")) {
				body.getShipments().get(0).setPaymentMode(v);
			} else if (k.equalsIgnoreCase("returnCountry")) {
				body.getShipments().get(0).setReturnCountry(v);
			} else if (k.equalsIgnoreCase("returnPhone")) {
				body.getShipments().get(0).setReturnPhone(v);
			} else if (k.equalsIgnoreCase("returnState")) {
				body.getShipments().get(0).setReturnState(v);
			} else if (k.equalsIgnoreCase("shippingMode")) {
				body.getShipments().get(0).setShippingMode(v);
			} else if (k.equalsIgnoreCase("weight")) {
				body.getShipments().get(0).setWeight(v);
			} else if (k.equalsIgnoreCase("state")) {
				body.getShipments().get(0).setState(v);
			}

		}
		return body;
	}

	public static CustomQc customQc(HashMap<String, Object> data) {
		List<Question> listquestions = new ArrayList<>();
		listquestions.add(question(data));
		listquestions.add(question(data));
		CustomQc body = CustomQc.builder()
				.questions(listquestions)
				.images( new ArrayList<Object>(Arrays.asList("","")))
				.brand("test")
				.item("test")
				.description("")
				.returnReason("")
				.quantity("")
				.productCategory("")
				.build();
		//write map for data entry set for above payload
		//including all keys for CustomQc dict for meesho3 api
		for (Map.Entry<String, Object> set : data.entrySet()) {
			String k = set.getKey();
			Object v = set.getValue();

			if (k.equalsIgnoreCase("brand")) {
				body.setBrand(v);
			} else if (k.equalsIgnoreCase("item")) {
				body.setItem(v);
			} else if (k.equalsIgnoreCase("description")) {
				body.setDescription(v);
			} else if (k.equalsIgnoreCase("returnReason")) {
				body.setReturnReason(v);
			} else if (k.equalsIgnoreCase("quantity")) {
				body.setQuantity(v);
			} else if (k.equalsIgnoreCase("productCategory")) {
				body.setProductCategory(v);
			}
		}
		return body;
	}

	public static Question question(HashMap<String, Object> data) {
		Question body = Question.builder()
				.questionsId("")
				.type("")
				.value(new ArrayList<Object>(Arrays.asList("","")))
				.options(new ArrayList<Object>(Arrays.asList("","")))
				.required("")
				.build();
		//write map for data entry set for above payload
		//including all keys for CustomQc dict for meesho3 api
		for (Map.Entry<String, Object> set : data.entrySet()) {
			String k = set.getKey();
			Object v = set.getValue();

			if (k.equalsIgnoreCase("questionsId")) {
				body.setQuestionsId(v);
			} else if (k.equalsIgnoreCase("type")) {
				body.setType(v);
			} else if (k.equalsIgnoreCase("required")) {
				body.setRequired(v);
			}
		}
		return body;
	}

















}
