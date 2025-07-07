
import "strconv"

func validIPAddress(queryIP string) string {
	if len(queryIP) < 4 {
		return "Neither"
	}
	for _, c := range queryIP {
		if c == '.' {
			return validateIPv4(queryIP)
		}
		if c == ':' {
			return validateIPv6(queryIP)
		}
	}
	return "Neither"
}

func validateIPv4(queryIP string) string {
	start := 0
	counter := 0
	for i, c := range queryIP {
		if c == '.' {
			if !validIPv4Component(queryIP[start:i]) {
				return "Neither"
			}
			counter++
			start = i + 1
		}
	}
	if !validIPv4Component(queryIP[start:]) {
		return "Neither"
	}
	if counter == 3 {
		return "IPv4"
	} else {
		return "Neither"
	}

}

func validIPv4Component(comp string) bool {
	if len(comp) == 0 || len(comp) > 3 {
		return false
	}
	if len(comp) > 1 &&  comp[0] == '0' {
		return false
	}
	number, err := strconv.Atoi(comp)
	if err != nil {
		return false
	}
	if number > 255 {
		return false
	}
	return true

}

func validateIPv6(queryIP string) string {
	start := 0
	counter := 0

	for i, c := range queryIP {
		if c == ':' {
			if !validIPv6Component(queryIP[start:i]) {
				return "Neither"
			}
			start = i + 1
			counter++
		}
	}
	if !validIPv6Component(queryIP[start:]) {
		return "Neither"
	}
	if counter == 7 {
		return "IPv6"
	} else {
		return "Neither"
	}
}

func validIPv6Component(comp string) bool {
	if len(comp) == 0 || len(comp) > 4 {
		return false
	}
	for _, c := range comp {
		if !(c >= 'A' && c <= 'F') && !(c >= 'a' && c <= 'f') && !(c >= '0' && c <= '9') {
			return false
		}
	}

	return true

}