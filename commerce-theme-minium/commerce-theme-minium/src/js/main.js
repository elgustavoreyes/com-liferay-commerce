AUI().ready(

	/*
	This function gets loaded when all the HTML, not including the portlets, is
	loaded.
	*/

	function() {

		const searchBar = Liferay.component('search-bar');

		if (searchBar) {
			searchBar.on('toogled', function(status) {
				document.querySelectorAll(".js-toggle-search").forEach(el => {
					el.classList.toggle("is-active", status);
				});

				document.getElementById("minium")
					.classList.toggle("has-search", status);
			});

		// 	document.querySelectorAll(".js-toggle-search").forEach(el => {
		// 		el.classList.toggle("is-active", searchBar.active);
		// });
		}
	}
);

Liferay.Portlet.ready(

	/*
	This function gets loaded after each and every portlet on the page.

	portletId: the current portlet's id
	node: the Alloy Node object of the current portlet
	*/

	function(portletId, node) {
	}
);

Liferay.on(
	'allPortletsReady',

	/*
	This function gets loaded when everything, including the portlets, is on
	the page.
	*/

	function() {
		var jsScrollArea = document.querySelector(".js-scroll-area");
		var miniumTop = document.querySelector("[name=minium-top]");

		if ('IntersectionObserver' in window) {
			if (jsScrollArea && miniumTop) {
				new IntersectionObserver(
					entries => {
						if (document.getElementById("minium")) {
							document.getElementById("minium").classList.toggle("is-scrolled", !entries[0].isIntersecting);
						}
					},
					{
						// root: jsScrollArea,
						rootMargin: "0px",
						threshold: 1.0
					}
				).observe(miniumTop);
			}
		}

		let scrollThreshold = 100;
		let lastKnownScrollPosition = 0;
		let lastKnownScrollOffset = 0;
		let ticking = false;
		const miniumWrapper = document.getElementById("minium");
		window.addEventListener("scroll", function() {
			const offset = window.scrollY - lastKnownScrollPosition;
			lastKnownScrollPosition = window.scrollY;
			lastKnownScrollOffset =
				Math.sign(offset) === Math.sign(lastKnownScrollOffset)
					? lastKnownScrollOffset + offset
					: offset;
	
			if (!ticking) {
				window.requestAnimationFrame(() => {
					if (Math.abs(lastKnownScrollOffset) > scrollThreshold) {
						miniumWrapper.classList.add(`is-scrolling-${Math.sign(lastKnownScrollOffset) > 0 ? "down" : "up"}`);
						miniumWrapper.classList.remove(`is-scrolling-${Math.sign(lastKnownScrollOffset) > 0 ? "up" : "down"}`);
					}
					ticking = false;
				});
				ticking = true;
			}
		}, false);
	}
);